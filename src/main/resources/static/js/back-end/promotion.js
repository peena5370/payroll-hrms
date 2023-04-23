/**
 * @module  promotionModule
 * 
 * @method  {@link  loadPromotionTable}
 * @method  {@link  submitPromotion}
 * @method  {@link  editPromotion}
 * @method  {@link  submitEditPromotion}
 */

/**
 * Load promotion json into promotion table
 * @param   {Element} table
 * @returns list
 */
function loadPromotionTable(table) {
  $.getJSON($contextPath + 'promotion/list', function(resp) {
    var $table = $(table);
    $(function() {
      var data = resp;
      $table.bootstrapTable({
        data: data,
        columns: [
          {title: 'ID', field: 'pid', align: 'center'}, 
          {title: 'Employee Name', field: 'employeeName'}, 
          {title: 'Promoter', field: 'managerName'}, 
          {title: 'Current Salary', field: 'currentSalary',
          formatter: function(value, row, index) {
            return value.toFixed(2);
          }}, 
          {title: 'Promote Salary', field: 'promoteSalary',
          formatter: function(value, row, index) {
            return value.toFixed(2);
          }}, 
          {title: 'Promote Title', field: 'titleName'}, 
          {title: 'Promote Date', field: 'promoteDate'}, 
          {title: 'Comment', field: 'comment'}, 
          {title: 'Action', field: 'action', align: 'center', clickToSelect: false,
          formatter: function(value, row, index) {
            return "<div class='layui-btn-group'><button class='layui-btn layui-btn-sm layui-btn-primary' onclick='javascript:editPromotion(" + row.pid + ")'><i class='layui-icon layui-icon-edit'></i></button></div>"
          }}
        ]
      });
    });
  });
}

/**
 * Submit promotion form into server
 * @returns insert status
 */
function submitPromotion() {
  var data = {
    eid: $("#Modal1 input[name='e-id']").val(),
    mid: $("#m-id").val(),
    currentSalary: $("#Modal1 input[name='current-salary']").val(),
    promoteSalary: $("#Modal1 input[name='promote-salary']").val(),
    promoteDate: $("#Modal1 input[name='date']").val(),
    titleno: $("#Modal1 select[name='title-list']").val(),
    comment: $("#Modal1 input[name='comment']").val()
  };
  $.ajax({
    type: "POST",
    url: $contextPath + "promotion/insert",
    contentType: "application/json; charset=utf-8",
    data: JSON.stringify(data),
    success: function (resp) {
      switch(resp) {
        case 1:
          alert('Information inserted.');
          location.reload();
          break;
        case 0:
          alert('Fail to insert information. Please try again later.');
          location.reload();
          $('#Modal1').modal('hide');
          break;
        default:
          break;
      }
    },
    error: function (err) {
      console.log(err);
    }
  });
}

/**
 * Load promotion info into form by id
 * @param {int} id 
 */
function editPromotion(id) {
  $.getJSON($contextPath + 'promotion/list/information/' + id, function(resp){
    $("#Modal2 input[name='id']").val(resp.pid);
    $("#Modal2 input[name='current-salary']").val(resp.currentSalary);
    $("#Modal2 input[name='promote-salary']").val(resp.promoteSalary);
    $("#Modal2 select[name='title-list']").val(resp.titleno);
    $("#Modal2 input[name='date']").val(resp.promoteDate);
    $("#Modal2 input[name='comment']").val(resp.comment);
    $('#Modal2').modal('show');
  });
}

/**
 * Submit edit promotion form into server
 * @returns update status
 */
function submitEditPromotion() {
  var data = {
    pid: $("#Modal2 input[name='id']").val(),
    currentSalary: $("#Modal2 input[name='current-salary']").val(),
    promoteSalary: $("#Modal2 input[name='promote-salary']").val(),
    promoteDate: $("#Modal2 input[name='allowance']").val(),
    titleno: $("#Modal2 select[name='title-list']").val(),
    comment: $("#Modal2 input[name='comment']").val()
  };
  console.log(data);
  $.ajax({
    type: "PUT",
    url: $contextPath + "promotion/list/information/" + data.pid + "/update",
    contentType: "application/json; charset=utf-8",
    data: JSON.stringify(data),
    success: function (resp) {
      switch(resp) {
        case 1:
          alert('Information updated.');
          location.reload();
          break;
        case 0:
          alert('Fail to update information. Please try again later.');
          location.reload();
          $('#Modal2').modal('hide');
          break;
        default:
          break;
      }
    },
    error: function (err) {
      console.log(err);
    }
  });
}
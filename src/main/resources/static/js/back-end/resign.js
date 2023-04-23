/**
 * @module  resignModule
 * 
 * @method  {@link  loadResignTable}
 * @method  {@link  submitResign}
 * @method  {@link  editResign}
 * @method  {@link  submitEditResign}
 * @method  {@link  approveResign}
 * @method  {@link  removeResign}
 */

/**
 * Loads resign json into resign table
 * @param   {Element} table
 * @returns list
 */
function loadResignTable(table) {
  $.getJSON($contextPath + 'resign/list', function(resp) {
    var $table = $(table);
    $(function() {
      var data = resp;
      $table.bootstrapTable({
        data: data,
        columns: [
          {title: 'ID', field: 'resignId', align: 'center'}, 
          {title: 'Employee Name', field: 'employeeName'}, 
          {title: 'Reason', field: 'reason'}, 
          {title: 'Resign Date', field: 'resignDate'}, 
          {title: 'Status', field: 'resignStatus',
          formatter: function(value, row, index) {
            switch(value) {
              case 0:
                return "<i class='layui-icon layui-icon-log' style='color: #1E9FFF;'>Pending</i>";
              case 1:
                return "<i class='layui-icon layui-icon-ok-circle' style='color: #5FB878;'>Approved</i>";
              default:
                break;
            } 
          }}, 
          {title: 'Approver', field: 'managerName'}, 
          {title: 'Action', field: 'action', align: 'center', clickToSelect: false,
          formatter: function(value, row, index) {
            return  "<div class='layui-btn-group'>" + 
                    "<button class='layui-btn layui-btn-sm layui-btn-primary' onclick='javascript:editResign(" + row.resignId + ")'><i class='layui-icon layui-icon-edit'></i></button>" + 
                    "<button class='layui-btn layui-btn-sm layui-btn-normal' onclick='javascript:approveResign(" + row.resignId + ")'><i class='layui-icon layui-icon-set-sm'></i></button>" + 
                    "<button class='layui-btn layui-btn-sm layui-btn-danger' onclick='javascript:removeResign(" + row.resignId + ")'><i class='layui-icon layui-icon-delete'></i></button>" + 
                    "</div>"
          }}
        ]
      });
    });
  });
}

/**
 * Submit resign form into server
 * @returns insert status
 */
function submitResign() {
  var data = {
    eid: $("#Modal1 input[name='e-id']").val(),
    mid: $("#m-id").val(),
    reason: $("#Modal1 input[name='reason']").val(),
    resignStatus: $("#Modal1 input[name='status']:checked").val(),
    resignDate: $("#Modal1 input[name='date']").val()
  };
  $.ajax({
    type: "POST",
    url: $contextPath + "resign/insert",
    contentType: "application/json; charset=utf-8",
    data: JSON.stringify(data),
    success: function (resp) {
      switch(resp) {
        case 1:
          alert('Resignation application inserted.');
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
 * Load resign info into form by id
 * @param {int} id 
 */
function editResign(id) {
  $.getJSON($contextPath + 'resign/list/information/' + id, function(res){
    $("#Modal2 input[name='id']").val(res.resignId);
    $("#Modal2 input[name='e-id']").val(res.eid);
    $("#Modal2 input[name='reason']").val(res.reason);
    $("#Modal2 input[name='date']").val(res.resignDate);
    $("#Modal2 input[name='status'][value='" + res.resignStatus + "']").prop('checked', true);
    $('#Modal2').modal('show');
  });
}

/**
 * Submit edit resign form into server
 * @returns update status
 */
function submitEditResign() {
  var data = {
    resignId: $("#Modal2 input[name='id']").val(),
    eid: $("#Modal2 select[name='e-id']").val(),
    reason: $("#Modal2 input[name='reason']").val(),
    resignDate: $("#Modal2 input[name='date']").val(),
    resignStatus: $("#Modal2 input[name='status']:checked").val()
  };
  $.ajax({
    type: "PUT",
    url: $contextPath + "resign/list/information/" + data.resignId + "/update",
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

/**
 * Approve resign application by id
 * @param {int} id 
 */
function approveResign(id) {
  if(confirm("Approve the resignation application?")) {
    var data = {
      mid: $('#m-id').val(),
      resignStatus: 1
    }
    $.ajax({
      type: "PUT",
      url: $contextPath + "resign/list/information/" + id + "/update/status",
      contentType: "application/json; charset=utf-8",
      data: JSON.stringify(data),
      success: function (resp) {
        switch(resp) {
          case 1:
            alert('Resignation application approved.');
            location.reload();
            break;
          case 0:
            alert('Fail to approve resign application. Please try again later.');
            location.reload();
            break;
          default:
            break;
        }
      },
      error: function (err) {
      console.log(err);
      }
      });
  } else {
    location.reload();
  }
}

/**
 * Remove resign info from table by id
 * @param {int} id 
 */
function removeResign(id) {
  if(confirm("Are you really sure?")) {
    $.ajax({
      type: "DELETE",
      url: $contextPath + "resign/list/information/" + id + "/delete",
      contentType: "application/json; charset=utf-8",
      success: function (resp) {
        switch(resp) {
          case 1:
            alert('Information deleted.');
            location.reload();
            break;
          case 0:
            alert('Fail to delete information. Please try again later.');
            location.reload();
            break;
          default:
            break;
        }
      },
      error: function (err) {
      console.log(err);
      }
      });
  } else {
    location.reload();
  }
}
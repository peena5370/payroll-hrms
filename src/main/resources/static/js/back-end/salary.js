/**
 * @module  salaryModule
 * 
 * @method  {@link  loadSalaryTable}
 * @method  {@link  submitSalary}
 * @method  {@link  editSalary}
 * @method  {@link  submitEditSalary}
 */

/**
 * Load salary json into salary table
 * @param   {Element} table
 * @returns list
 */
function loadSalaryTable(table) {
  $.getJSON($contextPath + 'salary/list', function(resp) {
    var $table = $(table);
    $(function() {
      var data = resp;
      $table.bootstrapTable({
        data: data,
        columns: [
          {title: 'ID', field: 'sid', align: 'center'}, 
          {title: 'Employee SAP ID', field: 'esapId', align: 'center'}, 
          {title: 'Employee Name', field: 'fullname'}, 
          {title: 'Monthly Salary', field: 'monthlySalary',
          formatter: function(value, row, index) {
            return value.toFixed(2);
          }}, 
          {title: 'Annual Salary', field: 'annualSalary',
          formatter: function(value, row, index) {
            return value.toFixed(2);
          }}, 
          {title: 'Date Update', field: 'dateUpdate'}, 
          {title: 'Action', field: 'action', align: 'center', clickToSelect: false,
          formatter: function(value, row, index) {
            return "<div class='layui-btn-group'><button class='layui-btn layui-btn-sm layui-btn-primary' onclick='javascript:editSalary(" + row.esapId + ")'><i class='layui-icon layui-icon-edit'></i></button></div>"
          }}
        ]
      });
    });
  });
}

/**
 * Submit salary form into server
 * @returns insert status
 */
function submitSalary() {
  var monthlySalary = $("#Modal1 input[name='monthly-salary']").val();
  var annualSalary = monthlySalary * 13;
  var data = {
    eid: $("#Modal1 input[name='e-id']").val(),
    monthlySalary: monthlySalary,
    annualSalary: annualSalary,
    dateUpdate: $("#Modal1 input[name='date']").val()
  };
  $.ajax({
    type: "POST",
    url: $contextPath + "salary/insert",
    contentType: "application/json; charset=utf-8",
    data: JSON.stringify(data),
    success: function (resp) {
      switch(resp) {
        case 1:
          alert('Salary information inserted.');
          location.reload();
          break;
        case 0:
          alert('Fail to insert salary information. Please try again later.');
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
 * Load salary info into edit form by id 
 * @param {int} id 
 */
function editSalary(id) {
  $.getJSON($contextPath + 'salary/list/information/' + id, function(resp){
    $("#Modal2 input[name='id']").val(resp.sid);
    $("#Modal2 input[name='e-id']").val(resp.eid);
    $("#Modal2 input[name='monthly-salary']").val(resp.monthlySalary);
    $("#Modal2 input[name='date']").val(resp.dateUpdate);
    $('#Modal2').modal('show');
  });
}

/**
 * Submit edit salary form into server
 * @returns update status
 */
function submitEditSalary() {
  var monthlySalary = $("#Modal2 input[name='monthly-salary']").val();
  var annualSalary = monthlySalary * 13;
  var data = {
    sid: $("#Modal2 input[name='id']").val(),
    eid: $("#Modal2 input[name='e-id']").val(),
    monthlySalary: monthlySalary,
    annualSalary: annualSalary,
    dateUpdate: $("#Modal2 input[name='date']").val()
  };
  $.ajax({
    type: "PUT",
    url: $contextPath + "salary/list/information/" + data.sid + "/update",
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
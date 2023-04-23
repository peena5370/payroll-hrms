/**
 * @module  loanModule
 * 
 * @method  {@link  loadLoanTable}
 * @method  {@link  approveLoan}
 * @method  {@link  rejectLoan}
 */

/**
 * Load loan json into loan table
 * @param   {Element} table
 * @returns list
 */
function loadLoanTable(table) {
  $.getJSON($contextPath + 'loan/list', function(resp) {
    var $table = $(table);
    $(function() {
      var data = resp;
      $table.bootstrapTable({
        data: data,
        columns: [
          {title: 'ID', field: 'loanId', align: 'center'},
          {title: 'Reference Number', field: 'refnum', width: '200'}, 
          {title: 'Application Name', field: 'employeeName'}, 
          {title: 'Reason', field: 'reason'}, 
          {title: 'Loan Amount', field: 'loanamount'}, 
          {title: 'Status', field: 'loanStatus', align: 'center',
          formatter: function(value, row, index) {
            switch(value) {
              case 0:
                return "<i class='layui-icon layui-icon-log' style='font-size: 20px; color: #1E9FFF;'></i>";
              case 1:
                return "<i class='layui-icon layui-icon-ok-circle' style='font-size: 20px; color: #5FB878;'></i>";
              case 2:
                return "<i class='layui-icon layui-icon-close-fill' style='font-size: 20px; color: #FF5722;'></i>";
              default:
                break;
            }
          }}, 
          {title: 'Date Apply', field: 'applicationDate'}, 
          {title: 'Date Approved', field: 'dateApproved'}, 
          {title: 'Approver', field: 'managerName'}, 
          {title: 'Action', field: 'action', align: 'left', valign: 'middle', clickToSelect: false,
          formatter: function(value, row, index) {
            return "<div class='layui-btn-group'><button class='layui-btn layui-btn-sm' style='width: 70px;' onclick='javascript:approveLoan(" + row.loanId + ")'>Approve</button><button class='layui-btn layui-btn-sm layui-btn-danger' style='width: 70px;' onclick='javascript:rejectLoan(" + row.loanId + ")'>Reject</button></div>"
          }}
        ]
      });
    });
  });
}

/**
 * Approve loan application by id
 * @param {int} id 
 */
function approveLoan(id) {
  var mId = $('#m-id').val();
  var date = new Date().toLocaleDateString("en-CA");
  var data = {
    loanId: id,
    mid: mId,
    loanStatus: 1,
    dateApproved: date
  };
  $.ajax({
    type: "PUT",
    url: $contextPath + "loan/list/information/" + data.loanId + "/update",
    contentType: "application/json; charset=utf-8",
    data: JSON.stringify(data),
    success: function (resp) {
      switch(resp) {
        case 1:
          alert('Loan application approved.');
          location.reload();
          break;
        case 0:
          alert('Fail to approve loan application. Please try again later.');
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
}

/**
 * Reject loan application by id
 * @param {int} id 
 */
function rejectLoan(id) {
  var mId = $('#m-id').val();
  var date = new Date().toLocaleDateString("en-CA");
  var data = {
    loanId: id,
    mid: mId,
    loanStatus: 2,
    dateApproved: date
  };
  $.ajax({
    type: "PUT",
    url: $contextPath + "loan/list/information/" + data.loanId + "/update",
    contentType: "application/json; charset=utf-8",
    data: JSON.stringify(data),
    success: function (res) {
      switch(res) {
        case 1:
          alert('Loan application rejected.');
          location.reload();
          break;
        case 0:
          alert('Fail to reject loan application. Please try again later.');
          loadLoanTable('#table');
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
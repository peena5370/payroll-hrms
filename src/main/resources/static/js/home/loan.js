/**
 * @module  loanModule
 * 
 * @method  {@link  loadLoanTable}
 * @method  {@link  submitLoan}
 */

/**
 * Load loan json into loan division
 * @param   {int} sapid
 * @returns list
 */
function loadLoanTable(sapid) {
  var row = "";
  var status = "";
  var dateApprove = "";
  var approver = "";
  $('#loan-list').find("div:gt(0)").remove();
  $.ajax({
    type: "GET",
    url: $contextPath + "loan/list/employee/" + sapid,
    dataType: "json",
    contentType: "application/json; charset=utf-8",
    success: function (resp) {
      $.each(resp, function (i, v) {
        switch(v.loanStatus) {
          case 0:
            status = "<span style='color: #0d6efd;'>Pending</span>";
            break;
          case 1:
            status = "<span style='color: #198754;'>Approved</span>";
            break;
          case 2:
            status = "<span style='color: #dc3545;'>Rejected</span>";
            break;
          default:
            break;
        }

        switch(v.managerName) {
          case null:
            approver = "";
            break;
          default:
            approver = v.managerName;
            break;
        }
        
        switch(v.dateApproved) {
          case null:
              dateApprove = "";
              break;
          default:
              dateApprove = v.dateApproved;
              break;
        }

        row =  "<div class='row mb-1' style='height: 250px;'><div class='col border'><div class='row'><div class='col-sm-2'>";
        row += "<label class='col-form-label'><strong>Reference Number</strong></label></div><div class='col-sm-3'>";
        row += "<label class='col-form-label'>" + v.refnum + "</label></div></div><div class='row'><div class='col-sm-2'>";
        row += "<label class='col-form-label'><strong>Loan Amount</strong></label></div><div class='col-sm-3'>";
        row += "<label class='col-form-label'>RM " + v.loanamount.toFixed(2) + "</label></div></div><div class='row'><div class='col-sm-2'>";
        row += "<label class='col-form-label'><strong>Status</strong></label></div><div class='col-sm-3'>";
        row += "<label class='col-form-label'>" + status + "</label></div></div><div class='row'><div class='col-sm-2'>";
        row += "<label class='col-form-label'><strong>Date Application</strong></label></div><div class='col-sm-3'>";
        row += "<label class='col-form-label'>" + v.applicationDate + "</label></div><div class='col-sm-2'>";
        row += "<label class='col-form-label'><strong>Date Approved</strong></label></div><div class='col-sm-3'>";
        row += "<label class='col-form-label'>" + dateApprove + "</label></div></div><div class='row'><div class='col-sm-2'>";
        row += "<label class='col-form-label'><strong>Approver</strong></label></div><div class='col-sm-3'>";
        row += "<label class='col-form-label'>" + approver + "</label></div></div><div class='row'><div class='col-sm-2'>";
        row += "<label class='col-form-label'><strong>Reason</strong></label></div><div class='col-sm-3'>";
        row += "<label class='col-form-label'>" + v.reason + "</label></div></div></div></div>";

      $("#loan-list").append(row);
    });
    },
    error: function (err) {
      console.log(err);
    }
  });
}

/**
 * Submit loan form into server
 * @returns insert status
 */
function submitLoan() {
  var data = {
    esapId: $("#Modal1 input[name='sapid']").val(),
    reason: $("#Modal1 input[name='reason']").val(),
    loanamount: $("#Modal1 input[name='loan-amount']").val()
  };
  $.ajax({
    type: "POST",
    url: $contextPath + "loan/insert/" + data.esapId,
    contentType: "application/json; charset=utf-8",
    data: JSON.stringify(data),
    success: function (resp) {
      switch(resp) {
        case 1:
          alert('Loan application submitted. Please meets with your supervisor for further approval.');
          location.reload();
          break;
        case 0:
          alert('Fail to submit loan application. Please try again later.');
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
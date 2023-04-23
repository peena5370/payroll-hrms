/**
 * @module  leaveModule
 * 
 * @method  {@link  loadLeaveTable}
 * @method  {@link  submitLeave}
 */

/**
 * Load leave json into leave division
 * @param   {int} sapid
 * @returns list
 */
function loadLeaveTable(sapid) {
  var row = "";
  var status = "";
  var dateApprove = "";
  var approver = "";
  $('#leave-list').find("div:gt(0)").remove();
  $.ajax({
    type: "GET",
    url: $contextPath + "leave/list/employee/" + sapid,
    dataType: "json",
    contentType: "application/json; charset=utf-8",
    success: function (resp) {
      $.each(resp, function (i, v) {
        switch(v.leaveStatus) {
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
        
        switch(v.approvedDate) {
          case null:
              dateApprove = "";
              break;
          default:
              dateApprove = v.approvedDate;
              break;
        }

        row = "<div class='row mb-1'><div class='col border'>";
        row += "<div class='row'><div class='col-sm-2'>";
        row += "<label class='col-form-label'><strong>Reference Number</strong></label></div><div class='col-sm-3'>";
        row += "<label class='col-form-label'>" + v.refnum +"</label></div><div class='col-sm-1'></div><div class='col-sm-2'>";
        row += "<label class='col-form-label'><strong>Leave Type</strong></label></div><div class='col-sm-3'>";
        row += "<label class='col-form-label'>" + v.leaveType + "</label></div></div><div class='row'><div class='col-sm-2'>";
        row += "<label class='col-form-label'><strong>Reason</strong></label></div><div class='col-sm-10'>";
        row += "<label class='col-form-label'>" + v.reason + "</label></div></div><div class='row'><div class='col-sm-2'>";
        row += "<label class='col-form-label'><strong>Date Start</strong></label></div><div class='col-sm-3'>";
        row += "<label class='col-form-label'>" + new Date(v.dateFrom).toLocaleString() + "</label></div><div class='col-sm-1'></div><div class='col-sm-2'>";
        row += "<label class='col-form-label'><strong>Date Application</strong></label></div><div class='col-sm-3'>";
        row += "<label class='col-form-label'>" + v.applicationDate + "</label></div></div><div class='row'><div class='col-sm-2'>";
        row += "<label class='col-form-label'><strong>Date End</strong></label></div><div class='col-sm-3'>";
        row += "<label class='col-form-label'>" + new Date(v.dateTo).toLocaleString() + "</label></div><div class='col-sm-1'></div><div class='col-sm-2'>";
        row += "<label class='col-form-label'><strong>Date Approved</strong></label></div><div class='col-sm-3'>";
        row += "<label class='col-form-label'>" + dateApprove + "</label></div></div><div class='row'><div class='col-sm-2'>";
        row += "<label class='col-form-label'><strong>Status</strong></label></div><div class='col-sm-3'>";
        row += "<label class='col-form-label'>" + status + "</label></div><div class='col-sm-1'></div><div class='col-sm-2'>";
        row += "<label class='col-form-label'><strong>Approver</strong></label></div><div class='col-sm-3'>";
        row += "<label class='col-form-label'>"+ approver + "</label></div></div></div></div>";

      $("#leave-list").append(row);
    });
    },
    error: function (err) {
      console.log(err);
    }
  });
}

/**
 * Submit leave form into server
 */
function submitLeave() {
  var startDate = $("#Modal1 input[name='date-start']").val();
  var startTime = $("#Modal1 input[name='time-start']").val();
  var startDateTime = startDate + "T" + startTime;
  var endDate = $("#Modal1 input[name='date-end']").val();
  var endTime = $("#Modal1 input[name='time-end']").val();
  var endDateTime = endDate + "T" + endTime;
  var data = {
    esapId: $("#Modal1 input[name='sapid']").val(),
    reason: $("#Modal1 input[name='reason']").val(),
    leaveType: $("#Modal1 select[name='l-type']").val(),
    dateFrom: startDateTime,
    dateTo: endDateTime
  };
  $.ajax({
    type: "POST",
    url: $contextPath + "leave/insert",
    contentType: "application/json; charset=utf-8",
    data: JSON.stringify(data),
    success: function (resp) {
      switch(resp) {
        case 1:
          alert('Leave application submitted. Please meets with your supervisor for further approval.');
          location.reload();
          break;
        case 0:
          alert('Fail to insert leave application. Please try again later.');
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
/**
 * @module  trainingModule
 * 
 * @method  {@link  loadTrainingTable}
 * @method  {@link  updateStatus}
 */

/**
 * Load training json into training division
 * @param   {int} sapid
 * @returns list
 */
function loadTrainingTable(sapid) {
  var row = "";
  var status = "";
  var dateApprove = "";
  var approver = "";
  $('#training-list').find("div:gt(0)").remove();
  $.ajax({
    type: "GET",
    url: $contextPath + "training/list/employee/" + sapid,
    dataType: "json",
    contentType: "application/json; charset=utf-8",
    success: function (resp) {
      $.each(resp, function (i, v) {
        switch(v.sessionStatus) {
          case 0:
            status = "<span style='color: #0d6efd;'>Pending</span>";
            break;
          case 1:
            status = "<span style='color: #198754;'>Completed</span>";
            break;
          case 2:
            status = "<span style='color: #dc3545;'>Canceled</span>";
            break;
          default:
            break;
        }

        row = "<div class='row mb-1'><div class='col border'><div class='row'><div class='col-sm-2'>";
        row += "<label class='col-form-label'><strong>ID</strong></label></div><div class='col-sm-3'>";
        row += "<label class='col-form-label'>" + v.tid + "</label></div><div class='col-sm-2'>";
        row += "<label class='col-form-label'><strong>Date Start</strong></label></div><div class='col-sm-3'>";
        row += "<label class='col-form-label'>" + new Date(v.startDate).toLocaleString() + "</label></div><div class='col-sm-2'>";
        row += "<button type='button' class='btn btn-outline-success btn-sm mt-2 float-end' onclick='javascript:updateStatus("+ v.tid + ");'>Complete</button>";
        row += "</div></div><div class='row'><div class='col-sm-2'>";
        row += "<label class='col-form-label'><strong>Issuer</strong></label></div><div class='col-sm-3'>";
        row += "<label class='col-form-label'>" + v.managerName + "</label></div><div class='col-sm-2'>";
        row += "<label class='col-form-label'><strong>Date End</strong></label></div><div class='col-sm-3'>";
        row += "<label class='col-form-label'>" + new Date(v.endDate).toLocaleString() + "</label></div></div><div class='row'><div class='col-sm-2'>";
        row += "<label class='col-form-label'><strong>Status</strong></label></div><div class='col-sm-8'>";
        row += "<label class='col-form-label'>" + status + "</label></div></div><div class='row'><div class='col-sm-2'>";
        row += "<label class='col-form-label'><strong>Title</strong></label></div><div class='col-sm-8'>";
        row += "<label class='col-form-label'>" + v.trainingTitle + "</label></div></div><div class='row'><div class='col-sm-2'>";
        row += "<label class='col-form-label'><strong>Description</strong></label></div></div><div class='row'><div class='col'>";
        row += "<label class='col-form-label'>" + v.description + "</label></div></div></div></div></div>";

      $("#training-list").append(row);
    });
    },
    error: function (err) {
      console.log(err);
    }
  });
}

/**
 * Update training status
 * @returns update status
 */
function updateStatus(id) {
  var sapid = $('#e-sapid').val();
  if(confirm("Training has been completed?")) {
    var data = {
      tid: id,
      esapId: sapid,
      sessionStatus: 1
    }
    $.ajax({
      type: "PUT",
      url: $contextPath + "training/list/employee/" + data.esapId + "/update/status",
      contentType: "application/json; charset=utf-8",
      data: JSON.stringify(data),
      success: function (resp) {
        switch(resp) {
          case 1:
            alert('Status updated.');
            location.reload();
            break;
          case 0:
            alert('Fail to update status. Please try again later.');
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
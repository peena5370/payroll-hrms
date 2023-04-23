/**
 * @module  trainingModule
 * 
 * @method  {@link  loadTrainingTable}
 * @method  {@link  submitTraining}
 * @method  {@link  editTraining}
 * @method  {@link  submitEditTraining}
 * @method  {@link  updateTrainingStatus}
 * @method  {@link  submitTrainingStatus}
 */

/**
 * Load training json into training table
 * @param   {Element} table
 * @returns list
 */
function loadTrainingTable(table) {
  $.getJSON($contextPath + 'training/list', function(res) {
    var $table = $(table);
    $(function() {
      var data = res;
      $table.bootstrapTable({
        data: data,
        columns: [
          {title: 'ID', field: 'tid', align: 'center'}, 
          {title: 'Employee Name', field: 'employeeName'}, 
          {title: 'Title', field: 'trainingTitle'}, 
          {title: 'Description', field: 'description'}, 
          {title: 'Date Start', field: 'startDate',
          formatter: function(value, row, index) {
            return new Date(value).toLocaleString();
          }}, 
          {title: 'Date Completed', field: 'endDate',
          formatter: function(value, row, index) {
            return new Date(value).toLocaleString();
          }}, 
          {title: 'Status', field: 'sessionStatus',
          formatter: function(value, row, index){
            switch(value) {
              case 0:
                return "<i class='layui-icon layui-icon-time' style='color: #1E9FFF;'></i>&nbsp;Pending";
              case 1:
                return "<i class='layui-icon layui-icon-ok-circle' style='color: #009688;'></i>&nbsp;Completed";
              case 2:
                return "<i class='layui-icon layui-icon-close-fill' style='color: #FF5722;'></i>&nbsp;Canceled";
              default: 
                break;
            }
          }}, 
          {title: 'Supervisor Name', field: 'managerName'}, 
          {title: 'Action', field: 'action', align: 'center', clickToSelect: false,
          formatter: function(value, row, index) {
            return  "<div class='layui-btn-group'>" + 
                    "<button class='layui-btn layui-btn-sm layui-btn-primary' id='test-btn' onclick='javascript:editTraining(" + row.tid + ")'><i class='layui-icon layui-icon-edit'></i></button>" + 
                    "<button class='layui-btn layui-btn-sm layui-btn-normal' onclick='javascript:updateTrainingStatus(" + row.tid + ", " + row.sessionStatus + ")'><i class='layui-icon layui-icon-set-sm'></i></button>" + 
                    "</div>"
          }}
        ]
      });
    });
  });
}

/**
 * Submit training form into server
 * @returns insert status
 */
function submitTraining() {
  var desc = $("#Modal1 textarea[name='desc']").val();
  var startDate = $("#Modal1 input[name='date-start']").val();
  var startTime = $("#Modal1 input[name='time-start']").val();
  var startDateTime = startDate + "T" + startTime;
  var endDate = $("#Modal1 input[name='date-end']").val();
  var endTime = $("#Modal1 input[name='time-end']").val();
  var endDateTime = endDate + "T" + endTime;
  var data = {
    trainingTitle: $("#Modal1 input[name='title']").val(),
    description: desc.replace(/\n/g, "<br>"),
    startDate: startDateTime,
    endDate: endDateTime,
    sessionStatus: $("#Modal1 input[name='status']:checked").val(),
    eid: $("#Modal1 input[name='e-id']").val(),
    mid: $("#m-id").val()
  };
  $.ajax({
    type: "POST",
    url: $contextPath + "training/insert",
    contentType: "application/json; charset=utf-8",
    data: JSON.stringify(data),
    success: function (resp) {
      switch(resp) {
        case 1:
          alert('Training information inserted.');
          location.reload();
          break;
        case 0:
          alert('Fail to insert training information. Please try again later.');
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
 * Load training info into edit form by id
 * @param {int} id
 */
function editTraining(id) {
  $("#Modal2 input[name='status']").prop('checked', false);
  $.getJSON($contextPath + 'training/list/information/' + id, function(resp){
    $("#Modal2 input[name='id']").val(resp.tid);
    $("#Modal2 input[name='e-id']").val(resp.eid);
    $("#Modal2 input[name='status'][value='" + resp.sessionStatus + "']").prop('checked', true);
    var startDateTime = resp.startDate.split("T");
    $("#Modal2 input[name='date-start']").val(startDateTime[0]);
    $("#Modal2 input[name='time-start']").val(startDateTime[1]);
    var endDateTime = resp.endDate.split("T");
    $("#Modal2 input[name='date-end']").val(endDateTime[0]);
    $("#Modal2 input[name='time-end']").val(endDateTime[1]);
    $("#Modal2 input[name='title']").val(resp.trainingTitle);
    var desc = resp.description;
    $("#Modal2 textarea[name='desc']").val(desc.replace(/\<br\>/g, "\n"));
    $('#Modal2').modal('show');
  });
}

/**
 * Submit edit training form into server
 * @returns update status
 */
function submitEditTraining() {
  var desc = $("#Modal2 textarea[name='desc']").val();
  var startDate = $("#Modal2 input[name='date-start']").val();
  var startTime = $("#Modal2 input[name='time-start']").val();
  var startDateTime = startDate + "T" + startTime;
  var endDate = $("#Modal2 input[name='date-end']").val();
  var endTime = $("#Modal2 input[name='time-end']").val();
  var endDateTime = endDate + "T" + endTime;
  var data = {
    tid: $("#Modal2 input[name='id']").val(),
    eid: $("#Modal2 input[name='e-id']").val(),
    mid: $("#m-id").val(),
    sessionStatus: $("#Modal2 input[name='status']:checked").val(),
    startDate: startDateTime,
    endDate: endDateTime,
    trainingTitle: $("#Modal2 input[name='title']").val(),
    description: desc.replace(/\n/g, "<br>")
  };
  $.ajax({
    type: "PUT",
    url: $contextPath + "training/list/information/" + data.tid + "/update",
    contentType: "application/json; charset=utf-8",
    data: JSON.stringify(data),
    success: function (resp) {
      switch(resp) {
        case 1:
          alert('Training information updated.');
          location.reload();
          break;
        case 0:
          alert('Fail to update training information. Please try again later.');
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
 * Load table id and status into form
 * @param {int} id 
 * @param {int} status 
 */
function updateTrainingStatus(id, status) {
  $("#Modal3 input[name='status']").prop('checked', false);
  $("#Modal3 input[name='id']").val(id);
  $("#Modal3 input[name='status'][value='" + status + "']").prop('checked', true);
  $('#Modal3').modal('show');
}

/**
 * Submit training status form into server
 * @returns update status
 */
function submitTrainingStatus() {
  var data = {
    mid: $("#m-id").val(),
    tid: $("#Modal3 input[name='id']").val(),
    sessionStatus: $("#Modal3 input[name='status']:checked").val()
  };
  $.ajax({
    type: "PUT",
    url: $contextPath + "training/list/information/" + data.tid + "/update/status",
    contentType: "application/json; charset=utf-8",
    data: JSON.stringify(data),
    success: function (resp) {
      switch(resp) {
        case 1:
          alert('Training status updated.');
          location.reload();
          break;
        case 0:
          alert('Fail to update training status. Please try again later.');
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
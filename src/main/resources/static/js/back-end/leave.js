/**
 * @module  leaveModule
 * 
 * @method  {@link  loadLeaveTable}
 * @method  {@link  approveLeave}
 * @method  {@link  rejectLeave}
 */

/**
 * Load leave json into leave table
 * @param   {Element} table
 * @returns list
 */
function loadLeaveTable(table) {
  $.getJSON($contextPath + 'leave/list', function(resp) {
    var $table = $(table);
    $(function() {
      var data = resp;
      $table.bootstrapTable({
        data: data,
        columns: [
          {title: 'ID', field: 'lid', align: 'center'},
          {title: 'Reference Number', field: 'refnum', width: '200'}, 
          {title: 'Application Name', field: 'employeeName'}, 
          {title: 'Reason', field: 'reason'}, 
          {title: 'Leave Type', field: 'leaveType'}, 
          {title: 'Status', field: 'leaveStatus', align: 'center',
          formatter: function(value, row, index) {
            switch(value) {
              case 0:
                return "<i class='layui-icon layui-icon-log' style='font-size: 20px; color: #1E9FFF;'></i>";
              case 1:
                return "<i class='layui-icon layui-icon-ok-circle' style='font-size: 20px; color: #0f0;'></i>";
              case 2:
                return "<i class='layui-icon layui-icon-close-fill' style='font-size: 20px; color: #F00;'></i>";
              default:
                break;
            }
          }}, 
          {title: 'Date Start', field: 'dateFrom',
          formatter: function(value, row, index) {
            return new Date(value).toLocaleString("en-CA");
          }}, 
          {title: 'Date End', field: 'dateTo',
          formatter: function(value, row, index) {
            return new Date(value).toLocaleString("en-CA");
          }}, 
          {title: 'Date Apply', field: 'applicationDate'}, 
          {title: 'Date Approved', field: 'approvedDate'}, 
          {title: 'Approver', field: 'managerName'}, 
          {title: 'Action', field: 'action', align: 'left', valign: 'middle', clickToSelect: false,
          formatter: function(value, row, index) {
            return  "<div class='layui-btn-group'>" + 
                    "<button class='layui-btn layui-btn-sm' style='width: 70px;' onclick='javascript:approveLeave(" + row.lid + ")'>Approve</button>" + 
                    "<button class='layui-btn layui-btn-sm layui-btn-danger' style='width: 70px;' onclick='javascript:rejectLeave(" + row.lid + ")'>Reject</button>" +
                    "</div>"
          }
        }],
      })
    })
  });
}

/**
 * Approve leave application by id
 * @param {int} id 
 */
function approveLeave(id) {
  var mId = $('#m-id').val();
  var date = new Date().toLocaleDateString("en-CA");
  var data = {
    lid: id,
    mid: mId,
    leaveStatus: 1,
    approvedDate: date
  }
  $.ajax({
    type: "PUT",
    url: $contextPath + "leave/list/information/" + data.lid + "/update",
    contentType: "application/json; charset=utf-8",
    data: JSON.stringify(data),
    success: function (resp) {
      switch(resp) {
        case 1:
          alert('Leave application approved.');
          location.reload();
          break;
        case 0:
          alert('Fail to approve leave application. Please try again later.');
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
 * Reject leave application by id
 * @param {int} id 
 */
function rejectLeave(id) {
  var mId = $('#m-id').val();
  var date = new Date().toLocaleDateString("en-CA");
  var data = {
    lid: id,
    mid: mId,
    leaveStatus: 2,
    approvedDate: date
  }
  $.ajax({
    type: "PUT",
    url: $contextPath + "leave/list/information/" + data.lid + "/update",
    contentType: "application/json; charset=utf-8",
    data: JSON.stringify(data),
    success: function (res) {
      switch(res) {
        case 1:
          alert('Leave application rejected.');
          location.reload();
          break;
        case 0:
          alert('Fail to reject leave application. Please try again later.');
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
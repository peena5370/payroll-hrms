/**
 * @module  employeeModule
 * 
 * @method  {@link  loadCountEmp}
 * @method  {@link  loadCountActiveEmp}
 * @method  {@link  loadEmployeeTable}
 * @method  {@link  loadEmployeeForManagerTable}
 * @method  {@link  submitEmployee}
 * @method  {@link  editEmployee}
 * @method  {@link  submitEditEmployee}
 * @method  {@link  updateEmployeeResign}
 * @method  {@link  submitUpdateEmployeeResign}
 * @method  {@link  removeEmployee}
 */

/**
 * Load quantity of all employee available in system
 * @param   {Element} tag
 * @returns integer 
 */
function loadCountEmp(tag) {
$.getJSON($contextPath + 'employee/list/count/all', function(json) {
  $(tag).append(json);
});
}

/**
 * Load quantity of employee that not yet resign
 * @param   {Element} tag 
 * @returns integer
 */
function loadCountActiveEmp(tag) {
  $.getJSON($contextPath + 'employee/list/count/active', function(json) {
    $(tag).append(json);
  });
}

/**
 * Loads employee json into admin employee table
 * @param   {Element} table
 * @returns list
 */
function loadEmployeeTable(table) {
  $.getJSON($contextPath + 'employee/list', function(res) {
    var $table = $(table);
    $(function() {
      var data = res;
      $table.bootstrapTable({
        data: data,
        columns: [
          {title: 'ID', field: 'eid', align: 'center'},
          {title: 'System ID', field: 'esapId', align: 'center'},
          {title: 'Full Name', field: 'fullname'},
          {title: 'Department', field: 'deptName'},
          {title: 'Position', field: 'titleName'},
          {title: 'Supervisor Name', field: 'managerName'},
          {title: 'Gender', field: 'gender'},
          {title: 'Date of Birth', field: 'dateOfBirth'},
          {title: 'Age', field: 'age', align: 'center'},
          {title: 'Martial Status', field: 'martialStatus'},
          {title: 'Education Level', field: 'education'},
          {title: 'Home Address', field: 'address'},
          {title: 'State', field: 'state'},
          {title: 'Living Country', field: 'country'},
          {title: 'Phone Number', field: 'phone'}, 
          {title: 'Email Address', field: 'email'},
          {title: 'Date Hired', field: 'dateHired'},
          {title: 'Date Resign', field: 'dateResign',
          formatter: function(value, row, index) {
            if(value === null) {
              return "";
            } else {
              return value;
            }
          }}, 
          {title: 'Action', field: 'action', align: 'left', valign: 'middle', clickToSelect: false,
          formatter: function(value, row, index) {
            return  "<div class='layui-btn-group'>" + 
                    "<button class='layui-btn layui-btn-sm layui-btn-primary' onclick='javascript:editEmployee(" + row.eid + ")'><i class='layui-icon layui-icon-edit'></i></button>" + 
                    "<button class='layui-btn layui-btn-sm layui-btn-danger' onclick='javascript:removeEmployee(" + row.eid + ")'><i class='layui-icon layui-icon-delete'></i></button>" + 
                    "</div>"
          }}
        ],
      })
    })
  });
}

/**
 * Loads employee json into manager table
 * @param   {Element} table
 * @returns list
 */
function loadEmployeeForManagerTable(table) {
  $.getJSON($contextPath + 'employee/list', function(resp) {
    var $table = $(table);
    $(function() {
      var data = resp;
      $table.bootstrapTable({
        data: data,
        columns: [
          {title: 'ID', field: 'eid', align: 'center'},
          {title: 'System ID', field: 'esapId', align: 'center'},
          {title: 'Full Name', field: 'fullname'},
          {title: 'Department', field: 'deptName'},
          {title: 'Position', field: 'titleName'},
          {title: 'Supervisor Name', field: 'managerName'},
          {title: 'Gender', field: 'gender'},
          {title: 'Date of Birth', field: 'dateOfBirth'},
          {title: 'Age', field: 'age', align: 'center'},
          {title: 'Martial Status', field: 'martialStatus'},
          {title: 'Education Level', field: 'education'},
          {title: 'Home Address', field: 'address'},
          {title: 'State', field: 'state'},
          {title: 'Living Country', field: 'country'},
          {title: 'Phone Number', field: 'phone'}, 
          {title: 'Email Address', field: 'email'}, 
          {title: 'Date Hired', field: 'dateHired'}, 
          {title: 'Date Resign', field: 'dateResign',
          formatter: function(value, row, index) {
            if(value === null) {
              return "";
            } else {
              return value;
            }
          }}, 
          {title: 'Action', field: 'action', align: 'left', valign: 'middle', clickToSelect: false,
          formatter: function(value, row, index) {
            return  "<div class='layui-btn-group'>" + 
                    "<button class='layui-btn layui-btn-sm layui-btn-primary' onclick='javascript:editEmployee(" + row.eid + ")'><i class='layui-icon layui-icon-edit'></i></button>" + 
                    "<button class='layui-btn layui-btn-sm layui-btn-normal' onclick='javascript:updateEmployeeResign(" + row.esapId + ")'><i class='layui-icon layui-icon-set-sm'></i></button>" + 
                    "<button class='layui-btn layui-btn-sm layui-btn-danger' onclick='javascript:removeEmployee(" + row.eid + ")'><i class='layui-icon layui-icon-delete'></i></button>" + 
                    "</div>"
          }}
        ],
      })
    })
  });
}

/**
 * Submit employee form into server
 * @returns Insert status
 */
function submitEmployee() {
  var addr = $("#Modal1 textarea[name='addr']").val();
  var data = {
    fullname: $("#Modal1 input[name='name']").val(),
    gender: $("#Modal1 input[name='gender']:checked").val(),
    dateOfBirth: $("#Modal1 input[name='dob']").val(),
    aid: $("#Modal1 input[name='account-id']").val(),
    mid: $("#Modal1 input[name='m-id']").val(),
    deptno: $("#Modal1 select[name='dept-list']").val(),
    titleno: $("#Modal1 select[name='title-list']").val(),
    age: $("#Modal1 input[name='age']").val(),
    martialStatus: $("#Modal1 input[name='martial-status']").val(),
    education: $("#Modal1 input[name='education']").val(),
    address: addr.replace(/\n/g, " "),
    state: $("#Modal1 select[name='state']").val(),
    country: $("#Modal1 input[name='country']").val(),
    phone: $("#Modal1 input[name='phone']").val(),
    email: $("#Modal1 input[name='email']").val(),
    dateHired: $("#Modal1 input[name='date-hire']").val()
  };
  $.ajax({
    type: "POST",
    url: $contextPath + "employee/insert",
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
 * Load json into and render data into employee form
 * @param {int} id 
 */
function editEmployee(id) {
  $("#Modal2 input[name='gender']").prop('checked', false);
  $.getJSON($contextPath + 'employee/list/information/' + id, function(resp){
    $("#Modal2 input[name='sapid']").val(resp.esapId);
    $("#Modal2 input[name='name']").val(resp.fullname);
    $("#Modal2 input[name='gender'][value='" + resp.gender + "']").prop('checked', true);
    $("#Modal2 input[name='dob']").val(resp.dateOfBirth);
    $("#Modal2 select[name='dept-list']").val(resp.deptno);
    $("#Modal2 select[name='title-list']").val(resp.titleno);
    $("#Modal2 input[name='age']").val(resp.age);
    $("#Modal2 input[name='martial-status']").val(resp.martialStatus);
    $("#Modal2 input[name='education']").val(resp.education);
    $("#Modal2 textarea[name='addr']").val(resp.address);
    $("#Modal2 select[name='state']").val(resp.state);
    $("#Modal2 input[name='country']").val(resp.country);
    $("#Modal2 input[name='phone']").val(resp.phone);
    $("#Modal2 input[name='email']").val(resp.email);
    $('#Modal2').modal('show');
  });
}

/**
 * Submit edit form into server
 */
function submitEditEmployee() {
  var addr = $("#Modal2 textarea[name='addr']").val();
  var data = {
    esapId: $("#Modal2 input[name='sapid']").val(),
    fullname: $("#Modal2 input[name='name']").val(),
    gender: $("#Modal2 input[name='gender']:checked").val(),
    dateOfBirth: $("#Modal2 input[name='dob']").val(),
    deptno: $("#Modal2 select[name='dept-list']").val(),
    titleno: $("#Modal2 select[name='title-list']").val(),
    age: $("#Modal2 input[name='age']").val(),
    martialStatus: $("#Modal2 input[name='martial-status']").val(),
    education: $("#Modal2 input[name='education']").val(),
    address: addr.replace(/\n/g, " "),
    state: $("#Modal2 select[name='state']").val(),
    country: $("#Modal2 input[name='country']").val(),
    phone: $("#Modal2 input[name='phone']").val(),
    email: $("#Modal2 input[name='email']").val()
  };
  $.ajax({
    type: "PUT",
    url: $contextPath + "employee/list/information/" + data.esapId + "/update",
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
 * Load employee resign info by SAP ID
 * @param {int} sapid 
 */
function updateEmployeeResign(sapid) {
  $("#Modal3 input[name='sapid']").val(sapid);
  $('#Modal3').modal('show');
}

/**
 * Submit employee resign status into server
 * @returns Update status
 */
function submitUpdateEmployeeResign() {
  var data = {
    esapId: $("#Modal3 input[name='sapid']").val(),
    dateResign: $("#Modal3 input[name='resign-date']").val()
  };
  $.ajax({
    type: "PUT",
    url: $contextPath + "employee/list/information/" + data.esapId + "/update-resign",
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
          $('#Modal3').modal('hide');
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
 * Remove employee info from table
 * @param {int} id 
 */
function removeEmployee(id) {
  if(confirm("Are you really sure?")) {
    $.ajax({
      type: "DELETE",
      url: $contextPath + "employee/list/information/" + id + "/delete",
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
    console.log('Error');
  }
}
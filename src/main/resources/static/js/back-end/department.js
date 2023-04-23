/**
 * @module  departmentModule
 * 
 * @method  {@link  loadCountDept}
 * @method  {@link  loadDepartmentTable}
 * @method  {@link  submitDepartment}
 * @method  {@link  editDepartment}
 * @method  {@link  submitEditDepartment}
 * @method  {@link  removeDepartment}
 * @method  {@link  loadDepartmentList}
 * 
 */

/**
 * Load quantity of available departments in the system
 * @param   {Element} tag
 * @returns integer
 */
function loadCountDept(tag) {
  $.getJSON($contextPath + 'department/list/count/all', function(json) {
    $(tag).append(json);
  });
}

/**
 * Load json data into department table
 * @param {Element}  table
 * @returns list
 */
function loadDepartmentTable(table) {
  $.getJSON($contextPath + 'department/list/detailed', function(resp) {
    var $table = $(table);
    $(function() {
      var data = resp;
      $table.bootstrapTable({
        data: data,
        columns: [
          {title: 'Dept No.', field: 'deptno', align: 'center'},
          {title: 'Location Address', field: 'location'},
          {title: 'Department Name', field: 'deptName'},
          {title: 'State', field: 'state'},
          {title: 'Country', field: 'country'},
          {title: 'Department Manager', field: 'managerName'}, 
          {title: 'Phone', field: 'phoneNum'}, 
          {title: 'Action', field: 'action', align: 'left', valign: 'middle', clickToSelect: false,
          formatter: function(value, row, index) {
            return  "<div class='layui-btn-group'>" + 
                    "<button class='layui-btn layui-btn-sm layui-btn-primary' onclick='javascript:editDepartment(" + row.deptno + ")'><i class='layui-icon layui-icon-edit'></i></button>" + 
                    "<button class='layui-btn layui-btn-sm layui-btn-danger' onclick='javascript:removeDepartment(" + row.deptno + ")'><i class='layui-icon layui-icon-delete'></i></button>" + 
                    "</div>"
          }}
        ],
      })
    })
  });
}

/**
 * Submit form info into server
 * @returns Submit status
 */
function submitDepartment() {
  var addr = $("#Modal1 textarea[name='addr']").val();
  var data = {
    deptName: $("#Modal1 input[name='dept-name']").val(),
    mid: $("#Modal1 input[name='m-id']").val(),
    location: addr.replace(/\n/g, " "),
    state: $("#Modal1 select[name='state']").val(),
    country: $("#Modal1 input[name='country']").val()
  };
  $.ajax({
    type: "POST",
    url: $contextPath + "department/insert",
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
 * Load json data into form
 * @param {int} id 
 */
function editDepartment(id) {
  $.getJSON($contextPath + 'department/list/base/information/' + id, function(resp){
    $("#Modal2 input[name='deptno']").val(resp.deptno);
    $("#Modal2 input[name='m-id']").val(resp.mid);
    $("#Modal2 input[name='dept-name']").val(resp.deptName);
    $("#Modal2 textarea[name='addr']").val(resp.location);
    $("#Modal2 select[name='state']").val(resp.state);
    $("#Modal2 input[name='country']").val(resp.country);
    $('#Modal2').modal('show');
  });
}

/**
 * Submit edit form to server
 * @returns Update status
 */
function submitEditDepartment() {
  var addr = $("#Modal2 textarea[name='addr']").val();
  var data = {
  deptno: $("#Modal2 input[name='deptno']").val(),
  mid: $("#Modal2 input[name='m-id']").val(),
  deptName: $("#Modal2 input[name='dept-name']").val(),
  location: addr.replace(/\n/g, " "),
  state: $("#Modal2 select[name='state']").val(),
  country: $("#Modal2 input[name='country']").val(),
  };
  $.ajax({
    type: "PUT",
    url: $contextPath + "department/list/base/information/" + data.deptno + "/update",
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
 * Remove department info from table
 * @param {int} id 
 */
function removeDepartment(id) {
  if(confirm("Are you really sure?")) {
    $.ajax({
      type: "DELETE",
      url: $contextPath + "department/list/base/information/" + id + "/delete",
      contentType: "application/json; charset=utf-8",
      success: function (res) {
        switch(res) {
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

/**
 * Load department list into \<select\>\<option\> tag
 * @param {Element} sel 
 */
function loadDepartmentList(sel) {
  $.getJSON($contextPath + 'department/list/drop-down', function(resp) {
    var row = "";
    $.each(resp, function (i, v) {
      row += "<option value='" + v.deptno + "'>" + v.deptno + ", " + v.deptName + "</option>";
    });
    $(sel).append(row);
  });
}
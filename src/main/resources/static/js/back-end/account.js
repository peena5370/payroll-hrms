/**
 * @module  accountModule
 * 
 * @method  {@link  loadAccQty}
 * @method  {@link  loadActiveAccQty}
 * @method  {@link  loadAccountTable}
 * @method  {@link  loadManagerAccountTable}
 * @method  {@link  submitAccount}
 * @method  {@link  editAccount}
 * @method  {@link  submitEditAccount}
 * @method  {@link  editStatus}
 * @method  {@link  submitUpdateStatus}
 * @method  {@link  removeAccount}
 * @method  {@link  submitUpdatePassword}
 * 
 * @update  15-11-2022: Added new methods {@link analyzeStrength} and {@link validatePassword}
 */

/**
 * Loads all account quantity
 * @param   {Element} tag
 * @returns integer
 */
function loadAccQty(tag) {
$.getJSON($contextPath + 'account/list/count/all', function(json) {
  $(tag).append(json);
});
}

/**
 * Loads active account quantity
 * @param   {Element} tag
 * @returns integer
 */
function loadActiveAccQty(tag) {
  $.getJSON($contextPath + 'account/list/count/active', function(json) {
    $(tag).append(json);
  });
}

/**
 * Load json data into table
 * @param {Element} table
 * @returns list
 */
function loadAccountTable(table) {
  $.getJSON($contextPath + 'account/list', function(resp) {
    var $table = $(table);
    $(function() {
      var data = resp;
      $table.bootstrapTable({
        data: data,
        columns: [
          {title: 'ID', field: 'id', align: 'center'}, 
          {title: 'Username', field: 'username'}, 
          {title: 'Employee SAP ID', field: 'esapId', align: 'center',
          formatter: function(value, row, index) {
            switch(value){
              case 0:
                return "";
              default:
                return value;
            }
          }}, 
          {title: 'Manager SAP ID', field: 'msapId', align: 'center',
          formatter: function(value, row, index) {
            switch(value){
              case 0:
                return "";
              default:
                return value;
            }
          }}, 
          {title: 'Date Created', field: 'dateCreated',
          formatter: function(value, row, index) {
            return new Date(value).toLocaleString();
          }}, 
          {title: 'Date Modified', field: 'dateChanged',
          formatter: function(value, row, index) {
            if(value === null) {
              return "";
            } else {
              return new Date(value).toLocaleString();
            }
          }}, 
          {title: 'Status', field: 'accountStatus', align: 'center',
          formatter: function(value, row, index) {
            if(value === 1) {
              return "<i class='layui-icon layui-icon-ok-circle' style='font-size: 20px; color: #0f0;'></i>";
            } else {
              return "<i class='layui-icon layui-icon-close-fill' style='font-size: 20px; color: #f00;'></i>";
            }
          }}, 
          {title: 'Action', field: 'state', align: 'left', valign: 'middle', clickToSelect: false,
          formatter: function(value, row, index) {
            return  "<div class='layui-btn-group'>" +
                    "<button class='layui-btn layui-btn-sm layui-btn-primary' onclick='javascript:editAccount(" + row.id + ")'><i class='layui-icon layui-icon-edit'></i></button>" +
                    "<button class='layui-btn layui-btn-sm layui-btn-normal' onclick='javascript:editStatus(" + row.id + ")'><i class='layui-icon layui-icon-set-sm'></i></button>" +
                    "<button class='layui-btn layui-btn-sm layui-btn-danger' onclick='javascript:removeAccount(" + row.id + ")'><i class='layui-icon layui-icon-delete'></i></button>" + 
                    "</div>"
          }
        }],
      })
    })
  });
}

/**
 * Load account json into manager table
 * @param {Element} table 
 */
function loadManagerAccountTable(table) {
  $.getJSON($contextPath + 'account/list/employee', function(resp) {
    var $table = $(table);
    $(function() {
      var data = resp;
      $table.bootstrapTable({
        data: data,
        columns: [
          {title: 'ID', field: 'id', align: 'center'}, 
          {title: 'Username', field: 'username'}, 
          {title: 'Status', field: 'accountStatus', align: 'center',
          formatter: function(value, row, index) {
            if(value === 1) {
              return "<i class='layui-icon layui-icon-ok-circle' style='font-size: 20px; color: #0f0;'></i>";
            } else {
              return "<i class='layui-icon layui-icon-close-fill' style='font-size: 20px; color: #f00;'></i>";
            }
          }}, 
          {title: 'Employee SAP ID', field: 'esapId', align: 'center',
          formatter: function(value, row, index) {
            if(value === 0) {
              return "";
            } else {
              return value;
            }
          }}, 
          {title: 'Manager SAP ID', field: 'msapId', align: 'center',
          formatter: function(value, row, index) {
            if(value === 0) {
              return "";
            } else {
              return value;
            }
          }}, 
          {title: 'Employee Full Name', field: 'fullname',
          formatter: function(value, row, index) {
            if(value == null) {
              return "";
            } else {
              return value;
            }
          }}, 
          {title: 'Action', field: 'action', align: 'left', valign: 'middle', clickToSelect: false,
          formatter: function(value, row, index) {
            return  "<div class='layui-btn-group'>" + 
                    "<button class='layui-btn layui-btn-sm layui-btn-primary' onclick='javascript:editAccount(" + row.id + ")'><i class='layui-icon layui-icon-edit'></i></button>" + 
                    "<button class='layui-btn layui-btn-sm layui-btn-danger' onclick='javascript:removeAccount(" + row.id + ")'><i class='layui-icon layui-icon-delete'></i></button>" + 
                    "</div>"
          }}
        ]
      });
    });
  });
}

/**
 * Submit account form as json to server
 * @returns Account submit status
 */
function submitAccount() {
  if(validatePassword() === 1) {
    var data = {
      username: $("#Modal1 input[name='username']").val(),
      password: $("#Modal1 input[name='pass']").val()
    };
    $.ajax({
      type: "POST",
      url: $contextPath + "account/register",
      contentType: "application/json; charset=utf-8",
      data: JSON.stringify(data),
      success: function (resp) {
        switch(resp) {
          case 1:
            alert('Register account successful.');
            location.reload();
            break;
          case 0:
            alert('Fail to register account. Please try again later.');
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
  } else {
    alert("Password and confirm password mismatch. Please check again before submit.");
    location.reload();
  }
  
}

/**
 * Load selected account info into form
 * @param {int} id 
 */
function editAccount(id) {
  $.getJSON($contextPath + 'account/list/information/' + id, function(resp){
    $("#Modal2 input[name='id']").val(resp.id);
    $("#Modal2 input[name='username']").val(resp.username);
    $('#Modal2').modal('show');
  });
}

/**
 * Submit account info as json to server
 * @returns Update status
 */
function submitEditAccount() {
  var data = {
  id: $("#Modal2 input[name='id']").val(),
  username: $("#Modal2 input[name='username']").val(),
  password: $("#Modal2 input[name='pass']").val()
  };
  $.ajax({
    type: "PUT",
    url: $contextPath + "account/list/information/" + data.id + "/update/password",
    contentType: "application/json; charset=utf-8",
    data: JSON.stringify(data),
    success: function (resp) {
      switch(resp) {
        case 1:
          alert('Account updated.');
          location.reload();
          break;
        case 0:
          alert('Fail to update account. Please try again later.');
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
 * Load Account status information into form
 * @param {int} id
 */
function editStatus(id) {
  $.getJSON($contextPath + 'account/list/information/' + id, function(resp){
    $("#Modal3 input[name='id']").val(resp.id);
    $("#Modal3 input[name='username']").val(resp.username);
    $("#Modal3 input[name='status'][value='" + resp.accountStatus + "']").prop('checked', true);
    $('#Modal3').modal('show');
  });
}

/**
 * Submit account info as json to server
 * @returns Update status
 */
 function submitUpdateStatus() {
  var data = {
  id: $("#Modal3 input[name='id']").val(),
  username: $("#Modal3 input[name='username']").val(),
  accountStatus: $("#Modal3 input[name='status']:checked").val()
  };
  $.ajax({
    type: "PUT",
    url: $contextPath + "account/list/information/" + data.id + "/update/status",
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
 * Remove account info.
 * @param {int} id
 * @returns
 */
function removeAccount(id) {
  if(confirm("Are you really sure?")) {
    $.ajax({
      type: "DELETE",
      url: $contextPath + "account/list/delete/" + id,
      contentType: "application/json; charset=utf-8",
      success: function (resp) {
        switch(resp) {
          case 1:
            alert('Account deleted.');
            location.reload();
            break;
          case 0:
            alert('Fail to delete account. Please try again later.');
            loadAccountTable('#table');
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
    return 0;
  }
}

/**
 * Submit profile password changes into server
 * @param   {string}  username
 * @returns Update status
 */
 function submitUpdatePassword(username) {
  var nPass = $("input[name='n-pass']").val();
  var cPass = $("input[name='c-pass']").val();
  
  if(nPass == cPass) {
    var data = {
      username: username,
      password: $("input[name='n-pass']").val()
    }
    $.ajax({
      type: "PUT",
      url: $contextPath + "account/profile/update/password",
      contentType: "application/json; charset=utf-8",
      data: JSON.stringify(data),
      success: function (res) {
        switch(res) {
          case 1:
            alert('Password updated. Please relogin for changes made.');
            window.location.href = $contextPath + "back-end/logout";
            break;
          case 0:
            alert('Fail to update password. Please try again later.');
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
    alert('Password not match, please check again.');
  }
}

/**
 * Regex method for validating string strength.
 * @param {Element} elemId
 * @param {Element} modalInput
 * @strongRegex Must have atleast 8 characters with atleat 1 lowercase, 1 uppercase, 1 symbol and 1 number
 * @mediumRegex Must have atleat 6 character with atleast 1 lowercase or uppercase and 1 number
 */
 function analyzeStrength(elemId, modalInput) { 
  $(elemId).removeClass("bg-success bg-warning bg-danger");
  var strongRegex = new RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})");
  var mediumRegex = new RegExp("^(((?=.*[a-z])(?=.*[A-Z]))|((?=.*[a-z])(?=.*[0-9]))|((?=.*[A-Z])(?=.*[0-9])))(?=.{6,})");
  var strPass = $(modalInput).val();

  if(strongRegex.test(strPass)){
    $(elemId).css("width", "75%");
    $(elemId).addClass("bg-success");
  } else if(mediumRegex.test(strPass)){
    $(elemId).css("width", "50%");
    $(elemId).addClass("bg-warning");
  } else if(strPass == ''){
    $(elemId).removeClass("bg-success bg-warning bg-danger");
  } else {
    $(elemId).css("width", "25%");
    $(elemId).addClass("bg-danger");
  }
}

/**
 * Validate password and confirmed password string.
 * @TODO
 * @param {Element} classNameSuccess 
 * @param {Element} classNameFail 
 * @param {Element} modalInputOne 
 * @param {Element} modalInputTwo 
 * @returns 
 */
function validatePassword(classNameSuccess, classNameFail, modalInputOne, modalInputTwo) {
  $(classNameSuccess).removeClass("d-none");
  $(classNameFail).removeClass("d-none");
  var pass = $(modalInputOne).val();
  var cPass = $(modalInputTwo).val();

  if(pass === cPass) {
    $(classNameFail).addClass("d-none");
    return 1;
  } else {
    $(classNameSuccess).addClass("d-none");
    return 0;
  }
}
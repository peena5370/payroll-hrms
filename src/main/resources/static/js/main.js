/* ---------------- Profile ---------------- */

function loadProfile(username) {
  $("#Profile1 input[name='gender']").prop('checked', false);
  $.getJSON($contextPath + 'manager/profile/name/' + username, function(res){
    $('#system-name').append(res.fullname);
    $('#m-id').val(res.mid);

    $("#sapid").append(res.msapId);
    $("#Profile1 input[name='fullname']").val(res.fullname);
    $("#Profile1 input[name='gender'][value='" + res.gender + "']").prop('checked', true);
    $("#Profile1 input[name='dob']").val(res.dateOfBirth);
    $("#dept-name").append(res.deptName);
    $("#title-name").append(res.titleName);
    $("#Profile1 input[name='age']").val(res.age);
    $("#Profile1 input[name='martial-status']").val(res.martialStatus);
    $("#Profile1 input[name='education']").val(res.education);
    $("#Profile1 textarea[name='addr']").val(res.address);
    $("#Profile1 select[name='state']").val(res.state);
    $("#Profile1 input[name='country']").val(res.country);
    $("#Profile1 input[name='phone']").val(res.phone);
    $("#Profile1 input[name='email']").val(res.email);

    renderLayUiForm();
  });
}

function submitEditProfile() {
  var addr = $("#Profile1 textarea[name='addr']").val();
  var data = {
    msapId: $('#sapid').text(),
    fullname: $("#Profile1 input[name='fullname']").val(),
    gender: $("#Profile1 input[name='gender']:checked").val(),
    dateOfBirth: $("#Profile1 input[name='dob']").val(),
    age: $("#Profile1 input[name='age']").val(),
    martialStatus: $("#Profile1 input[name='martial-status']").val(),
    education: $("#Profile1 input[name='education']").val(),
    address: addr.replace(/\n/g, " "),
    state: $("#Profile1 select[name='state']").val(),
    country: $("#Profile1 input[name='country']").val(),
    phone: $("#Profile1 input[name='phone']").val(),
    email: $("#Profile1 input[name='email']").val()
  }
  $.ajax({
    type: "PUT",
    url: $contextPath + "manager/profile/" + data.msapId + "/update",
    contentType: "application/json; charset=utf-8",
    data: JSON.stringify(data),
    success: function (res) {
      switch(res) {
        case 1:
          alert('Information updated.');
          location.reload();
          break;
        case 0:
          alert('Fail to update information. Please try again later.');
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

/* ---------------- Utilities ---------------- */

function exportFormatTable(table, type) {
  $(table).tableExport({
    type: type, 
    escape:'false'
  })
}

function renderLayUiForm() {
  layui.use('form', function() {
    var form = layui.form;

    form.render();
  });
}
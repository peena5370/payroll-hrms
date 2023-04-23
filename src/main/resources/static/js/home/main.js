/**
 * @module  mainModule
 * 
 * @method  {@link  loadProfile}
 * @method  {@link  submitEditProfile}
 * @method  {@link  loadSalary}
 * @method  {@link  submitUpdatePassword}
 * @method  {@link  loadStatementDate}
 * @method  {@link  viewPaySlip}
 * @method  {@link  loadPaySlip}
 */

/**
 * Load employee profile json into form
 * @param {int} sapid 
 */
function loadProfile(sapid) {
  $("#Profile1 input[name='gender']").prop('checked', false);
  $.getJSON($contextPath + 'employee/profile/' + sapid, function(resp){
    $('#full-name').append(resp.fullname);
    $('#e-sapid').val(resp.esapId);

    $("#Profile1 input[name='sapid']").val(resp.esapId);
    $("#Profile1 input[name='fullname']").val(resp.fullname);
    $("#Profile1 input[name='gender'][value='" + resp.gender + "']").prop('checked', true);
    $("#Profile1 input[name='dob']").val(resp.dateOfBirth);
    $("#Profile1 input[name='dept-name']").val(resp.deptName);
    $("#Profile1 input[name='title-name']").val(resp.titleName);
    $("#Profile1 input[name='age']").val(resp.age);
    $("#Profile1 input[name='martial-status']").val(resp.martialStatus);
    $("#Profile1 input[name='education']").val(resp.education);
    $("#Profile1 textarea[name='addr']").val(resp.address);
    $("#Profile1 select[name='state']").val(resp.state);
    $("#Profile1 input[name='country']").val(resp.country);
    $("#Profile1 input[name='phone']").val(resp.phone);
    $("#Profile1 input[name='email']").val(resp.email);
    $("#Profile1 input[name='date-hire']").val(resp.dateHired);
  });
}

/**
 * Submit edit employee profile form into server
 * @returns update status
 */
function submitEditProfile() {
  var addr = $("#Profile1 textarea[name='addr']").val();
  var data = {
    esapId: $("#Profile1 input[name='sapid']").val(),
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
    url: $contextPath + "employee/profile/" + data.esapId + "/update",
    contentType: "application/json; charset=utf-8",
    data: JSON.stringify(data),
    success: function (resp) {
      switch(resp) {
        case 1:
          alert('Profile updated.');
          location.reload();
          break;
        case 0:
          alert('Fail to update profile. Please try again later.');
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
 * Load salary json into form
 * @param {int} sapid 
 */
function loadSalary(sapid) {
  $.getJSON($contextPath + 'salary/list/information/' + sapid, function(resp){
    $("#Profile1 input[name='monthly-salary']").val("RM " + resp.monthlySalary);
    $("#Profile1 input[name='annual-salary']").val("RM " + resp.annualSalary);
  });
}

/**
 * Submit update password form into server
 * @returns update status
 */
function submitUpdatePassword() {
  var newPass = $("#Profile2 input[name='n-pass']").val();
  var confirmPass = $("#Profile2 input[name='c-pass']").val();
  if(newPass == confirmPass) {
    var data = {
      esapId: $("#Profile1 input[name='sapid']").val(),
      password: newPass
    };
    $.ajax({
      type: "PUT",
      url: $contextPath + "employee/profile/" + data.esapId + "/update/password",
      contentType: "application/json; charset=utf-8",
      data: JSON.stringify(data),
      success: function (resp) {
        switch(resp) {
          case 1:
            alert('Password changed. Please relogin for the changes made.');
            window.location.href = $contextPath + "home/logout";
            break;
          case 0:
            alert('Fail to change password. Please try again later.');
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
    alert("Password not match. Please check again.");
    location.reload();
  }
  
  
}

/**
 * Load statement date by sap id
 * @TODO  insert back-end url into viewPaySlip parameter
 * @param {int} id 
 */
function loadStatementDate(sapid) {
  var row = "";
  $('#table').find("tr:gt(0)").remove();
  $.ajax({
    type: "GET",
    url: $contextPath + "payroll/list/information/date/" + sapid,
    dataType: "json",
    contentType: "application/json; charset=utf-8",
    success: function (resp) {
      $.each(resp, function (i, v) {
      row = "<tr>";
      row += "<td>" + v.paymentDate + "</td>";
      row += "<td>" + v.paymentDate.replace(/-/g, "\/") + "-" + v.esapId + "</td>";
      row += "<td><button class='btn btn-outline-primary btn-sm'" + 
              "onclick=\"javascript:viewPaySlip(\'" + $contextPath + "home\/payslip?date\=" + v.paymentDate + "\&sapid\=" + v.esapId + "\');\">View</button>";
      row += "</tr>";
      $("#table").append(row);
    });
    },
    error: function (err) {
      console.log(err);
    }
  });
}

/**
 * Passing url into viewPaySlip and open popup window for showing payslip
 * @TODO
 * @param {string} url
 */
function viewPaySlip(url) {
  window.open(url, '_blank', 'height=700,width=850,left=150,top=100,resizable=yes,scrollbars=yes,toolbar=yes,menubar=no,location=no,directories=no, status=yes');
}

/**
 * Load payslip json into form
 * @param {string} date 
 * @param {int} id 
 */
function loadPaySlip(date, id) {
  $.getJSON($contextPath + 'payroll/employee/' + date + '/' + id, function(resp){
    $("#fullname").append(resp.employeeName);
    $("#Form1 input[name='name']").val(resp.employeeName);
    $("#Form1 input[name='sapid']").val(resp.esapId);
    $("#Form1 input[name='dept-name']").val(resp.deptName);
    $("#Form1 input[name='epf-no']").val(resp.epfNo);
    $("#Form1 input[name='title-name']").val(resp.titleName);
    $("#Form1 input[name='income-tax']").val(resp.incomeTaxNo);
    $("#Form1 input[name='bank-acc']").val(resp.accountNum);
    $("#Form1 input[name='period']").val(resp.payPeriod);
    $("#Form1 input[name='basic-salary']").val(resp.basicPay.toFixed(2));
    $("#Form1 input[name='emp-epf']").val(resp.employeeEpf.toFixed(2));
    $("#Form1 input[name='ot-pay']").val(resp.overtimePay.toFixed(2));
    $("#Form1 input[name='emp-socso']").val(resp.employeeSocso.toFixed(2));
    $("#Form1 input[name='allowance']").val(resp.allowance.toFixed(2));
    $("#Form1 input[name='emp-eis']").val(resp.employeeEis.toFixed(2));
    $("#Form1 input[name='transport']").val(resp.transport.toFixed(2));
    $("#Form1 input[name='mtd-pcb']").val(resp.mtdPcb.toFixed(2));
    $("#Form1 input[name='other-deduction']").val(resp.otherDeduction.toFixed(2));
    $("#Form1 input[name='total']").val(resp.totalPay.toFixed(2));
    $("#Form1 input[name='empr-epf']").val(resp.employerEpf.toFixed(2));
    $("#Form1 input[name='empr-socso']").val(resp.employerSocso.toFixed(2));
    $("#Form1 input[name='empr-eis']").val(resp.employerEis.toFixed(2));
  });
}
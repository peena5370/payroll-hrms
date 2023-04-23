/**
 * @module  payrollModule
 * 
 * @method  {@link  loadPayrollTable}
 * @method  {@link  submitPayroll}
 * @method  {@link  rejectLoan}
 */

/**
 * Loads payroll json into payroll table
 * @param   {Element} table
 * @returns list
 */
function loadPayrollTable(table) {
  $.getJSON($contextPath + 'payroll/list', function(resp) {
    var $table = $(table);
    $(function() {
      var data = resp;
      $table.bootstrapTable({
        data: data,
        columns: [
          [{title: 'ID', field: 'prId', rowspan: 2, align: 'center', sortable: true}, 
          {title: 'Employee SAP ID', field: 'esapId', rowspan: 2, align: 'center', sortable: true}, 
          {title: 'Employee Name', field: 'employeeName', rowspan: 2}, 
          {title: 'Basic Salary', field: 'basicPay', rowspan: 2,
          formatter: function(value, row, index) {
            return value.toFixed(2);
          }}, 
          {title: 'Overtime Pay', field: 'overtimePay', rowspan: 2,
          formatter: function(value, row, index) {
            return value.toFixed(2);
          }}, 
          {title: 'Allowance', field: 'allowance', rowspan: 2,
          formatter: function(value, row, index) {
            return value.toFixed(2);
          }}, 
          {title: 'Transport Allowance', field: 'transport', rowspan: 2,
          formatter: function(value, row, index) {
            return value.toFixed(2);
          }},
          {title: 'Other Deduction', field: 'otherDeduction', rowspan: 2,
          formatter: function(value, row, index) {
            return value.toFixed(2);
          }}, 
          {title: 'Employee', colspan: 3, align: 'center'}, 
          {title: 'Employer', colspan: 3, align: 'center'}, 
          {title: 'MTD/PCB', field: 'mtdPcb', rowspan: 2,
          formatter: function(value, row, index) {
            return value.toFixed(2);
          }}, 
          {title: 'Total Payment', field: 'totalPay', rowspan: 2,
          formatter: function(value, row, index) {
            return value.toFixed(2);
          }}, 
          {title: 'Payment Period', field: 'payPeriod', rowspan: 2}, 
          {title: 'Payment Date', field: 'paymentDate', rowspan: 2, sortable: true}, 
          {title: 'Action', field: 'action', rowspan: 2, align: 'center', clickToSelect: false,
          formatter: function(value, row, index) {
            return  "<div class='layui-btn-group'><button class='layui-btn layui-btn-sm layui-btn-primary' onclick='javascript:editPayroll(" + row.prId + ")'><i class='layui-icon layui-icon-edit'></i></button></div>"
          }}],
          [{title: 'EPF', field: 'employeeEpf',
          formatter: function(value, row, index) {
            return value.toFixed(2);
          }}, 
          {title: 'SOCSO', field: 'employeeSocso',
          formatter: function(value, row, index) {
            return value.toFixed(2);
          }}, 
          {title: 'EIS', field: 'employeeEis',
          formatter: function(value, row, index) {
            return value.toFixed(2);
          }}, 
          {title: 'EPF', field: 'employerEpf',
          formatter: function(value, row, index) {
            return value.toFixed(2);
          }}, 
          {title: 'SOCSO', field: 'employerSocso',
          formatter: function(value, row, index) {
            return value.toFixed(2);
          }}, 
          {title: 'EIS', field: 'employerEis',
          formatter: function(value, row, index) {
            return value.toFixed(2);
          }}]
        ]
      });
    });
  });
}

/**
 * Submit payroll form into server
 * @returns insert status
 */
function submitPayroll() {
  var date = $("#Modal1 input[name='date-from']").val() + " to " + $("#Modal1 input[name='date-to']").val();
  var data = {
    basicPay: $("#Modal1 input[name='basic-salary']").val(),
    overtimePay: $("#Modal1 input[name='ot-pay']").val(),
    allowance: $("#Modal1 input[name='allowance']").val(),
    transport: $("#Modal1 input[name='transport']").val(),
    otherDeduction: $("#Modal1 input[name='other-deduction']").val(),
    employeeEpf: $("#Modal1 input[name='emp-epf']").val(),
    employeeSocso: $("#Modal1 input[name='emp-socso']").val(),
    employeeEis: $("#Modal1 input[name='emp-eis']").val(),
    employerEpf: $("#Modal1 input[name='empr-epf']").val(),
    employerSocso: $("#Modal1 input[name='empr-socso']").val(),
    employerEis: $("#Modal1 input[name='empr-eis']").val(),
    mtdPcb: $("#Modal1 input[name='mtd-pcb']").val(),
    totalPay: $("#Modal1 input[name='total']").val(),
    payPeriod: date,
    paymentDate: $("#Modal1 input[name='date']").val(),
    eid: $("#Modal1 input[name='e-id']").val()
  };
  $.ajax({
    type: "POST",
    url: $contextPath + "payroll/insert",
    contentType: "application/json; charset=utf-8",
    data: JSON.stringify(data),
    success: function (resp) {
      switch(resp) {
        case 1:
          alert('Payroll information inserted.');
          location.reload();
          break;
        case 0:
          alert('Fail to insert payroll information. Please try again later.');
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
 * Load payroll json into form by id
 * @param {int} id 
 */
function editPayroll(id) {
  $.getJSON($contextPath + 'payroll/list/information/' + id, function(resp){
    $("#Modal2 input[name='id']").val(resp.prId);
    $("#Modal2 input[name='e-id']").val(resp.eid);
    $("#Modal2 input[name='basic-salary']").val(resp.basicPay);
    $("#Modal2 input[name='ot-pay']").val(resp.overtimePay);
    $("#Modal2 input[name='allowance']").val(resp.allowance);
    $("#Modal2 input[name='transport']").val(resp.transport);
    $("#Modal2 input[name='other-deduction']").val(resp.otherDeduction);
    $("#Modal2 input[name='emp-epf']").val(resp.employeeEpf);
    $("#Modal2 input[name='emp-socso']").val(resp.employeeSocso);
    $("#Modal2 input[name='emp-eis']").val(resp.employeeEis);
    $("#Modal2 input[name='empr-epf']").val(resp.employerEpf);
    $("#Modal2 input[name='empr-socso']").val(resp.employerSocso);
    $("#Modal2 input[name='empr-eis']").val(resp.employerEis);
    $("#Modal2 input[name='mtd-pcb']").val(resp.mtdPcb);
    $("#Modal2 input[name='total']").val(resp.totalPay);
    var dateArr = resp.payPeriod.split(" to ");
    $("#Modal2 input[name='date-from']").val(dateArr[0]);
    $("#Modal2 input[name='date-to']").val(dateArr[1]);
    $("#Modal2 input[name='date']").val(resp.paymentDate);
    $('#Modal2').modal('show');
  });
}

/**
 * Submit edit payroll form into server
 * @returns update status
 */
function submitEditPayroll() {
  var date = $("#Modal2 input[name='date-from']").val() + " to " + $("#Modal2 input[name='date-to']").val();
  var data = {
    prId: $("#Modal2 input[name='id']").val(),
    eid: $("#Modal2 input[name='e-id']").val(),
    basicPay: $("#Modal2 input[name='basic-salary']").val(),
    overtimePay: $("#Modal2 input[name='ot-pay']").val(),
    allowance: $("#Modal2 input[name='allowance']").val(),
    transport: $("#Modal2 input[name='transport']").val(),
    otherDeduction: $("#Modal2 input[name='other-deduction']").val(),
    employeeEpf: $("#Modal2 input[name='emp-epf']").val(),
    employeeSocso: $("#Modal2 input[name='emp-socso']").val(),
    employeeEis: $("#Modal2 input[name='emp-eis']").val(),
    employerEpf: $("#Modal2 input[name='empr-epf']").val(),
    employerSocso: $("#Modal2 input[name='empr-socso']").val(),
    employerEis: $("#Modal2 input[name='empr-eis']").val(),
    mtdPcb: $("#Modal2 input[name='mtd-pcb']").val(),
    totalPay: $("#Modal2 input[name='total']").val(),
    payPeriod: date,
    paymentDate: $("#Modal2 input[name='date']").val()
  };
  $.ajax({
    type: "PUT",
    url: $contextPath + "payroll/list/information/" + data.prId + "/update",
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
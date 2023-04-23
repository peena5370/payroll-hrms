/**
 * @module  bankingModule
 * 
 * @method  {@link  loadBankingTable}
 * @method  {@link  submitBank}
 * @method  {@link  editBank}
 * @method  {@link  submitEditBank}
 * @method  {@link  removeBank}
 */

/**
 * Load banking json into banking table
 * @param   {Element} table
 * @returns list
 */
function loadBankingTable(table) {
  $.getJSON($contextPath + 'banking/list', function(resp) {
    var $table = $(table);
    $(function() {
      var data = resp;
      $table.bootstrapTable({
        data: data,
        columns: [
          {title: 'ID', field: 'bid', align: 'center'},
          {title: 'Employee SAP ID', field: 'esapId', align: 'center'}, 
          {title: 'Full Name', field: 'employeeName'}, 
          {title: 'Bank Name', field: 'bankName'}, 
          {title: 'Account Number', field: 'accountNum'}, 
          {title: 'EPF Number', field: 'epfNo'}, 
          {title: 'Income Tax No.', field: 'incomeTaxNo'}, 
          {title: 'Action', field: 'action', align: 'left', valign: 'middle', clickToSelect: false,
          formatter: function(value, row, index) {
            return  "<div class='layui-btn-group'>" + 
                    "<button class='layui-btn layui-btn-sm layui-btn-primary' onclick='javascript:editBank(" + row.bid + ")'><i class='layui-icon layui-icon-edit'></i></button>" + 
                    "<button class='layui-btn layui-btn-sm layui-btn-danger' onclick='javascript:removeBank(" + row.bid + ")'><i class='layui-icon layui-icon-delete'></i></button>" + 
                    "</div>"
          }}
        ]
      });
    });
  });
}

/**
 * Submit banking form into server
 * @returns Insert status
 */
function submitBank() {
  var data = {
    eid: $("#Modal1 input[name='e-id']").val(),
    bankName: $("#Modal1 input[name='bank-name']").val(),
    accountNum: $("#Modal1 input[name='account-num']").val(),
    epfNo: $("#Modal1 input[name='epf-no']").val(),
    incomeTaxNo: $("#Modal1 input[name='income-tax']").val()
  };
  $.ajax({
    type: "POST",
    url: $contextPath + "banking/insert",
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
 * Load banking info into form by id
 * @param {int} id 
 */
function editBank(id) {
  $.getJSON($contextPath + 'banking/list/information/' + id, function(resp){
    $("#Modal2 input[name='id']").val(resp.bid);
    $("#Modal2 input[name='e-id']").val(resp.eid);
    $("#Modal2 input[name='bank-name']").val(resp.bankName);
    $("#Modal2 input[name='account-num']").val(resp.accountNum);
    $("#Modal2 input[name='epf-no']").val(resp.epfNo);
    $("#Modal2 input[name='income-tax']").val(resp.incomeTaxNo);
    $('#Modal2').modal('show');
  });
}

/**
 * Submit edit form into server
 * @returns update status
 */
function submitEditBank() {
  var data = {
    bid: $("#Modal2 input[name='id']").val(),
    eid: $("#Modal2 input[name='e-id']").val(),
    bankName: $("#Modal2 input[name='bank-name']").val(),
    accountNum: $("#Modal2 input[name='account-num']").val(),
    epfNo: $("#Modal2 input[name='epf-no']").val(),
    incomeTaxNo: $("#Modal2 input[name='income-tax']").val()
  };
  $.ajax({
    type: "PUT",
    url: $contextPath + "banking/list/information/" + data.bid + "/update",
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
 * Remove bank info from table
 * @param {int} id 
 */
function removeBank(id) {
  if(confirm("Are you really sure?")) {
    $.ajax({
      type: "DELETE",
      url: $contextPath + "banking/list/information/" + id + "/delete",
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
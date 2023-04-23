/**
 * @module  titleModule
 * 
 * @method  {@link  loadCountTitle}
 * @method  {@link  loadTitleTable}
 * @method  {@link  submitTitle}
 * @method  {@link  editTitle}
 * @method  {@link  submitEditTitle}
 * @method  {@link  removeTitle}
 * @method  {@link  loadTitleList}
 */

/**
 * Load quantity of title in the system
 * @param   {Element} tag
 * @returns integer
 */
function loadCountTitle(tag) {
  $.getJSON($contextPath + 'title/list/count/all', function(json) {
    $(tag).append(json);
  });
}

/**
 * Load title json into title table
 * @param {Element} table
 * @returns list
 */
function loadTitleTable(table) {
  $.getJSON($contextPath + 'title/list', function(resp) {
    var $table = $(table);
    $(function() {
      var data = resp;
      $table.bootstrapTable({
        data: data,
        columns: [
          {title: 'No.', field: 'titleno', align: 'center'}, 
          {title: 'Title/Position', field: 'titleName'},
          {title: 'Description', field: 'titleDesc'}, 
          {title: 'Action', field: 'action', align: 'left', valign: 'middle', clickToSelect: false,
          formatter: function(value, row, index) {
            return  "<div class='layui-btn-group'>" + 
                    "<button class='layui-btn layui-btn-sm layui-btn-primary' onclick='javascript:editTitle(" + row.titleno + ")'><i class='layui-icon layui-icon-edit'></i></button>" + 
                    "<button class='layui-btn layui-btn-sm layui-btn-danger' onclick='javascript:removeTitle(" + row.titleno + ")'><i class='layui-icon layui-icon-delete'></i></button>" +
                    "</div>"
          }}
        ]
      });
    });
  });
}

/**
 * Submit title form into server
 * @returns Insert status
 */
function submitTitle() {
  var desc = $("#Modal1 textarea[name='desc']").val();
  var data = {
    titleName: $("#Modal1 input[name='title-name']").val(),
    titleDesc: desc.replace(/\n/g, " ")
  };
  $.ajax({
    type: "POST",
    url: $contextPath + "title/insert",
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
 * Load title info into form by id
 * @param {int} id 
 */
function editTitle(id) {
  $.getJSON($contextPath + 'title/list/information/' + id, function(resp){
    $("#Modal2 input[name='titleno']").val(resp.titleno);
    $("#Modal2 input[name='title-name']").val(resp.titleName);
    $("#Modal2 textarea[name='desc']").val(resp.titleDesc);
    $('#Modal2').modal('show');
  });
}

/**
 * Submit edit title info into server
 * @returns Update status
 */
function submitEditTitle() {
  var desc = $("#Modal2 textarea[name='desc']").val();
  var data = {
  titleno: $("#Modal2 input[name='titleno']").val(),
  titleName: $("#Modal2 input[name='title-name']").val(),
  titleDesc: desc.replace(/\n/g, " ")
  };
  $.ajax({
    type: "PUT",
    url: $contextPath + "title/list/information/" + data.titleno + "/update",
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
 * Remove title info from table
 * @param {int} id 
 */
function removeTitle(id) {
  if(confirm("Are you really sure?")) {
    $.ajax({
      type: "DELETE",
      url: $contextPath + "title/list/information/" + id + "/delete",
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
 * Load title json into \<select\>\<option\> tag
 * @param {Element} sel 
 */
function loadTitleList(sel) {
  $.getJSON($contextPath + 'title/list/drop-down', function(res) {
    var row = "";
    $.each(res, function (i, v) {
      row += "<option value='" + v.titleno + "'>" + v.titleno + ", " + v.titleName + "</option>";
    });
    $(sel).append(row);
  });
}
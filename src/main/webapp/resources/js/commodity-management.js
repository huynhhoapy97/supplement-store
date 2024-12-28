let isUpdateCommodity = false;

function getAllWithoutCommodity() {
    $.ajax({
        url: "category/get-all-without-commodity",
        type: "GET",
        dataType: "json",
        success: function(data){
            console.log({data: data});
            let dataArray = $.map(data, function (obj) {
              obj.text = obj.text || obj.name;

              return obj;
            });

            $("#category-list").select2().empty();
            $("#category-list").select2({
                data: dataArray
            });
            $("#category-list").val([]);
            $('#category-list').trigger('change');
        },
        error: function(xhr, status, errorThrown){
            alert(xhr.status);
        }
    });
}

function getCategoriesByCommodityId(commodityId) {
    return new Promise(function(resolve, reject) {
        $.ajax({
             url: "commodity/get-by-id",
             type: "GET",
             data: {commodity_id: commodityId},
             dataType: "json",
             success: function(data) {
                 resolve(data.selectedCategories);
             },
             error: function(xhr, status, errorThrown){
                 reject(xhr.status);
             }
        });
    });
}

$(document).ready(function(){
    getAllWithoutCommodity();

    $("#btn-commodity-save").click(function(){
        let formData = new FormData();
        let commodityId = document.getElementById("commodity-id").value;
        let commodityName = document.getElementById("commodity-name").value;
        let categoryIds = [];

        let selectedData = $("#category-list").find(":selected");
        if (selectedData.length > 0){
            for (let selected of selectedData){
                let categoryId = selected.attributes[0].value;
                categoryIds.push(categoryId);
            }
        }

        formData.append("commodityName", commodityName);
        formData.append("categoryIds", categoryIds);

        if (commodityName === ""){
            alert("Bạn chưa nhập TÊN mặt hàng");
            return;
        }

        if (!isUpdateCommodity){
            $.ajax({
                url: "commodity/save",
                type: "POST",
                data: formData,
                processData: false,
                contentType: false,
                success: function(data){
                    alert(data);

                    document.getElementById("commodity-name").value = '';

                    getAllWithoutCommodity();

                    $("#tbCommodity").DataTable().ajax.reload();
                },
                error: function(xhr, status, errorThrown){
                    alert(xhr.status + " - " + xhr.responseText);
                }
            });
        }
        else{
            formData.append("commodityId", commodityId);
            $.ajax({
                url: "commodity/update",
                type: "PUT",
                data: formData,
                processData: false,
                contentType: false,
                success: function(data){
                    alert(data);

                    document.getElementById("commodity-id").value = '';
                    document.getElementById("commodity-name").value = '';

                    getAllWithoutCommodity();

                    document.getElementById("btn-commodity-cancel").setAttribute("hidden", "hidden");
                    $("#tbCommodity").DataTable().ajax.reload();
                },
                error: function(xhr, status, errorThrown){
                    alert(xhr.status);
                }
            });
        }
    });

    $("#btn-commodity-cancel").click(function(){
        this.setAttribute("hidden", "hidden");

        document.getElementById("commodity-id").value = '';
        document.getElementById("commodity-name").value = '';

        getAllWithoutCommodity();

        isUpdateCommodity = false;
    });

    $("#tbCommodity").DataTable({
        ajax: {
            url: "commodity/get-all",
            dataSrc: ''
        },
        columns: [
            {
                targets: 0,
                data: 'name',
                className: 'commodity_info'
            },
            {
                targets: 1,
                data: 'createdDay',
                className: 'commodity_info',
                render: function (data) {
                    return new Date(data).toLocaleDateString("en-US");
                }
            },
            {
                targets: 2,
                data: 'id',
                className: 'commodity_non_info',
                render: function (data) {
                    return '<a class="delete-commodity" data-id="' + data + '">Xóa</a>';
                }
            }
        ],
        order: [[1, 'desc']]
    });

    let tbCommodity = new DataTable("#tbCommodity");
    tbCommodity.on('click', 'tbody tr td.commodity_info', function () {
        let data = tbCommodity.row(this).data();
        let commodity_id = data.id;

        $.ajax({
             url: "commodity/get-by-id",
             type: "GET",
             data: {commodity_id: commodity_id},
             dataType: "json",
             success: function(data){
                 console.log({data: data});
                 let categoryIds = [];
                 if (data.selectedCategories.length > 0){
                    for (let category of data.selectedCategories){
                        categoryIds.push(category.id);
                    }
                 }

                 let dataArray = $.map(data.allCategories, function (obj) {
                   obj.text = obj.text || obj.name;

                   return obj;
                 });

                 $("#category-list").select2().empty();
                 $("#category-list").select2({
                     data: dataArray
                 });
                 $("#category-list").val(categoryIds);
                 $('#category-list').trigger('change');
                 document.getElementById("commodity-id").value = data.id;
                 document.getElementById("commodity-name").value = data.name;

                 isUpdateCommodity = true;
             },
             error: function(xhr, status, errorThrown){
                 alert(xhr.status);
             }
        });

        document.getElementById("btn-commodity-cancel").removeAttribute("hidden");
    });

    tbCommodity.on('click', 'tbody tr td.commodity_non_info a.delete-commodity', function () {
         let commodity_id = this.attributes["data-id"].value;
         let formData = new FormData();
         formData.append("commodity_id", commodity_id)

         let text = "Bạn xóa mặt hàng này ?";
         if (confirm(text) === true) {
            getCategoriesByCommodityId(commodity_id)
                .then(function(categories) {
                    if (categories.length > 0) {
                        alert("Mặt hàng có chứa loại hàng. KHÔNG THỂ XÓA");
                    }
                    else {
                        $.ajax({
                            url: "commodity/delete",
                            type: "PUT",
                            data: formData,
                            processData: false,
                            contentType: false,
                            success: function(data){
                                alert(data);

                                document.getElementById("commodity-id").value = '';
                                document.getElementById("commodity-name").value = '';
                                document.getElementById("btn-commodity-cancel").setAttribute("hidden", "hidden");
                                $("#tbCommodity").DataTable().ajax.reload();
                            },
                            error: function(xhr, status, errorThrown){
                                Promise.reject(xhr.status);
                            }
                        });
                    }
                })
                .catch(function(errorStatus) {
                    alert(errorStatus);
                });
         }
    });
});
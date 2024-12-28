import {
	ClassicEditor,
	AccessibilityHelp,
	Autoformat,
	AutoImage,
	Autosave,
	Base64UploadAdapter,
	BlockQuote,
	Bold,
	Essentials,
	FontBackgroundColor,
	FontColor,
	FontFamily,
	FontSize,
	Heading,
	ImageBlock,
	ImageCaption,
	ImageInline,
	ImageInsert,
	ImageInsertViaUrl,
	ImageResize,
	ImageStyle,
	ImageTextAlternative,
	ImageToolbar,
	ImageUpload,
	Indent,
	IndentBlock,
	Italic,
	Link,
	LinkImage,
	List,
	ListProperties,
	MediaEmbed,
	Paragraph,
	PasteFromOffice,
	SelectAll,
	SourceEditing,
	Table,
	TableCaption,
	TableCellProperties,
	TableColumnResize,
	TableProperties,
	TableToolbar,
	TextTransformation,
	TodoList,
	Underline,
	Undo
} from 'ckeditor5';

const editorConfig = {
	toolbar: {
		items: [
			'undo',
			'redo',
			'|',
			'sourceEditing',
			'|',
			'heading',
			'|',
			'fontSize',
			'fontFamily',
			'fontColor',
			'fontBackgroundColor',
			'|',
			'bold',
			'italic',
			'underline',
			'|',
			'link',
			'insertImage',
			'mediaEmbed',
			'insertTable',
			'blockQuote',
			'|',
			'bulletedList',
			'numberedList',
			'todoList',
			'outdent',
			'indent'
		],
		shouldNotGroupWhenFull: false
	},
	plugins: [
		AccessibilityHelp,
		Autoformat,
		AutoImage,
		Autosave,
		Base64UploadAdapter,
		BlockQuote,
		Bold,
		Essentials,
		FontBackgroundColor,
		FontColor,
		FontFamily,
		FontSize,
		Heading,
		ImageBlock,
		ImageCaption,
		ImageInline,
		ImageInsert,
		ImageInsertViaUrl,
		ImageResize,
		ImageStyle,
		ImageTextAlternative,
		ImageToolbar,
		ImageUpload,
		Indent,
		IndentBlock,
		Italic,
		Link,
		LinkImage,
		List,
		ListProperties,
		MediaEmbed,
		Paragraph,
		PasteFromOffice,
		SelectAll,
		SourceEditing,
		Table,
		TableCaption,
		TableCellProperties,
		TableColumnResize,
		TableProperties,
		TableToolbar,
		TextTransformation,
		TodoList,
		Underline,
		Undo
	],
	fontFamily: {
		supportAllValues: true
	},
	fontSize: {
		options: [10, 12, 14, 'default', 18, 20, 22],
		supportAllValues: true
	},
	heading: {
		options: [
			{
				model: 'paragraph',
				title: 'Paragraph',
				class: 'ck-heading_paragraph'
			},
			{
				model: 'heading1',
				view: 'h1',
				title: 'Heading 1',
				class: 'ck-heading_heading1'
			},
			{
				model: 'heading2',
				view: 'h2',
				title: 'Heading 2',
				class: 'ck-heading_heading2'
			},
			{
				model: 'heading3',
				view: 'h3',
				title: 'Heading 3',
				class: 'ck-heading_heading3'
			},
			{
				model: 'heading4',
				view: 'h4',
				title: 'Heading 4',
				class: 'ck-heading_heading4'
			},
			{
				model: 'heading5',
				view: 'h5',
				title: 'Heading 5',
				class: 'ck-heading_heading5'
			},
			{
				model: 'heading6',
				view: 'h6',
				title: 'Heading 6',
				class: 'ck-heading_heading6'
			}
		]
	},
	image: {
		toolbar: [
			'toggleImageCaption',
			'imageTextAlternative',
			'|',
			'imageStyle:inline',
			'imageStyle:wrapText',
			'imageStyle:breakText',
			'|',
			'resizeImage'
		]
	},
	link: {
		addTargetToExternalLinks: true,
		defaultProtocol: 'https://',
		decorators: {
			toggleDownloadable: {
				mode: 'manual',
				label: 'Downloadable',
				attributes: {
					download: 'file'
				}
			}
		}
	},
	list: {
		properties: {
			styles: true,
			startIndex: true,
			reversed: true
		}
	},
	placeholder: 'Type or paste your content here!',
	table: {
		contentToolbar: ['tableColumn', 'tableRow', 'mergeTableCells', 'tableProperties', 'tableCellProperties']
	}
};

let categoryEditor;
let isUploadImage_Category = false;
let countImage_Category = 0;
let objUploadImage_Category = {};
let isUpdateCategory = false;

ClassicEditor
.create(document.querySelector("#category-description"), editorConfig)
.then(editor => {
    categoryEditor = editor;
} )
.catch(error => {
    console.error(error);
} );

$(document).ready(function(){
    $("#btn-category-save").click(function(){
        let formData = new FormData();
        let categoryId = document.getElementById("category-id").value;
        let categoryName = document.getElementById("category-name").value;
        let categoryCoverPhoto = document.getElementById("category-coverphoto").files[0];
        let categoryDescription = categoryEditor.getData();

        formData.append("categoryName", categoryName);
        formData.append("categoryCoverPhoto", categoryCoverPhoto);
        formData.append("categoryDescription", categoryDescription);

        if (categoryName === ""){
            alert("Bạn chưa nhập TÊN loại hàng");
            return;
        }

        if (!isUpdateCategory) {
            $.ajax({
                url: "category/save",
                type: "POST",
                data: formData,
                processData: false,
                contentType: false,
                success: function(data){
                    alert(data);

                    document.getElementById("category-name").value = '';
                    document.getElementById("category-coverphoto").value = '';
                    categoryEditor.setData('');

                    $("#tbCategory").DataTable().ajax.reload();
                },
                error: function(xhr, status, errorThrown){
                    alert(xhr.status);
                }
            });
        }
        else {
             formData.append("categoryId", categoryId);
             $.ajax({
                url: "category/update",
                type: "PUT",
                data: formData,
                processData: false,
                contentType: false,
                success: function(data){
                    alert(data);

                    document.getElementById("category-id").value = '';
                    document.getElementById("category-name").value = '';
                    document.getElementById("category-coverphoto").value = '';
                    categoryEditor.setData('');

                    document.getElementById("btn-category-cancel").setAttribute("hidden", "hidden");
                    $("#tbCategory").DataTable().ajax.reload();
                },
                error: function(xhr, status, errorThrown){
                    alert(xhr.status);
                }
            });
        }
    });

    $("#btn-category-cancel").click(function(){
        this.setAttribute("hidden", "hidden");

        document.getElementById("category-id").value = '';
        document.getElementById("category-name").value = '';
        categoryEditor.setData('');
        isUpdateCategory = false;
    });

    $("#btn-category-browse-image").click(function(){
        window.open("category/file-browse",
                  "_blank",
                  "width=700, height=500, top=0, left=960");
    });

    $("#btn-category-upload-image").click(function(){
        if (!isUploadImage_Category) {
            document.getElementById("btn-category-upload-image").innerText = "Đồng ý";
            document.getElementById("category-multiple-photo").removeAttribute("hidden");
            isUploadImage_Category = true;
        }
        else {
            if (countImage_Category == 0){
                alert("Chưa có ảnh nào được chọn");
            }
            else {
                let formData = new FormData();
                for (let file of objUploadImage_Category.target.files){
                    formData.append("files", file)
                }

                $.ajax({
                    url: "category/file-upload",
                    type: "POST",
                    data: formData,
                    processData: false,
                    contentType: false,
                    success: function(data){
                        alert(data);

                        document.getElementById("category-multiple-photo").value = '';
                        document.getElementById("btn-category-upload-image").innerText = "Tải ảnh";
                        document.getElementById("category-multiple-photo").setAttribute("hidden", "hidden");
                        isUploadImage_Category = false;
                        countImage_Category = 0;
                        objUploadImage_Category = {};
                    },
                    error: function(xhr, status, errorThrown){
                        alert(xhr.status);
                    }
                });
            }
        }
    });

    $("#category-multiple-photo").on("change", function(objEvent){
        if (objEvent.target.files.length == 0){
            countImage_Category = 0;
        }
        else {
            countImage_Category = objEvent.target.files.length;
            objUploadImage_Category = objEvent;
        }
    });

    $("#tbCategory").DataTable({
      ajax: {
        url: "category/get-all",
        dataSrc: ''
      },
      columns: [
        {
            targets: 0,
            data: 'name',
            className: 'category_info'
        },
        {
            targets: 1,
            data: 'createdDay',
            className: 'category_info',
            render: function (data) {
                return new Date(data).toLocaleDateString("en-US");
            }
        },
        {
            targets: 2,
            data: 'id',
            className: 'category_non_info',
            render: function (data) {
                return '<a class="delete-category" data-id="' + data + '">Xóa</a>';
            }
        }
      ],
      order: [[1, 'desc']]
    });

    let tbCategory = new DataTable('#tbCategory');
    tbCategory.on('click', 'tbody tr td.category_info', function () {
         let data = tbCategory.row(this).data();
         let category_id = data.id;

         $.ajax({
             url: "category/get-by-id",
             type: "GET",
             data: {category_id: category_id},
             dataType: "json",
             success: function(data){
                 console.log({data: data});

                 document.getElementById("category-id").value = data.id;
                 document.getElementById("category-name").value = data.name;
                 categoryEditor.setData(data.description);
                 isUpdateCategory = true;
             },
             error: function(xhr, status, errorThrown){
                 alert(xhr.status);
             }
         });

         document.getElementById("btn-category-cancel").removeAttribute("hidden");
    });

    tbCategory.on('click', 'tbody tr td.category_non_info a.delete-category', function () {
         let category_id = this.attributes["data-id"].value;
         let formData = new FormData();
         formData.append("category_id", category_id)

         let text = "Bạn xóa loại hàng này ?";
         if (confirm(text) === true) {
            $.ajax({
                url: "category/delete",
                type: "PUT",
                data: formData,
                processData: false,
                contentType: false,
                success: function(data){
                    alert(data);

                    document.getElementById("category-id").value = '';
                    document.getElementById("category-name").value = '';
                    document.getElementById("category-coverphoto").value = '';
                    categoryEditor.setData('');

                    document.getElementById("btn-category-cancel").setAttribute("hidden", "hidden");
                    $("#tbCategory").DataTable().ajax.reload();
                },
                error: function(xhr, status, errorThrown){
                    alert(xhr.status);
                }
            });
         }
    });
});


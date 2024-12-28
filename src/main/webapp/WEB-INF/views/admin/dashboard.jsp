<%@ page language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <base href="${pageContext.request.contextPath}/" />
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Bảng điều khiển</title>

    <!-- Google Font: Source Sans Pro -->
    <link rel="stylesheet"
      href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback" />
    <!-- Font Awesome -->
    <link rel="stylesheet" href="resources/adminlte/plugins/fontawesome-free/css/all.min.css" />
    <!-- Ionicons -->
    <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css" />
    <!-- Tempusdominus Bootstrap 4 -->
    <link rel="stylesheet"
      href="resources/adminlte/plugins/tempusdominus-bootstrap-4/css/tempusdominus-bootstrap-4.min.css" />
    <!-- iCheck -->
    <link rel="stylesheet" href="resources/adminlte/plugins/icheck-bootstrap/icheck-bootstrap.min.css" />
    <!-- JQVMap -->
    <link rel="stylesheet" href="resources/adminlte/plugins/jqvmap/jqvmap.min.css" />
    <!-- Theme style -->
    <link rel="stylesheet" href="resources/adminlte/dist/css/adminlte.min.css" />
    <!-- overlayScrollbars -->
    <link rel="stylesheet" href="resources/adminlte/plugins/overlayScrollbars/css/OverlayScrollbars.min.css" />
    <!-- Daterange picker -->
    <link rel="stylesheet" href="resources/adminlte/plugins/daterangepicker/daterangepicker.css" />
    <!-- summernote -->
    <link rel="stylesheet" href="resources/adminlte/plugins/summernote/summernote-bs4.min.css" />
    <!-- CKEditor -->
    <link rel="stylesheet" href="resources/css/ckeditor-style.css">
    <link rel="stylesheet" href="resources/ckeditor5/ckeditor5.css">
    <!-- Data Tables -->
    <link rel="stylesheet" href="resources/datatables/datatables.min.css" />
    <!-- Select2 -->
    <link rel="stylesheet" href="resources/select2/select2.css" />
  </head>

  <body class="hold-transition sidebar-mini layout-fixed">
    <div class="wrapper">
      <!-- Preloader -->
      <div class="preloader flex-column justify-content-center align-items-center">
        <img class="animation__shake" src="resources/adminlte/dist/img/AdminLTELogo.png" alt="AdminLTELogo" height="60"
          width="60" />
      </div>

      <!-- Navbar -->
      <nav class="main-header navbar navbar-expand navbar-white navbar-light">
        <!-- Left navbar links -->
        <ul class="navbar-nav">
          <li class="nav-item">
            <a class="nav-link" data-widget="pushmenu" href="#" role="button"><i class="fas fa-bars"></i></a>
          </li>
        </ul>

        <!-- Right navbar links -->
        <ul class="navbar-nav ml-auto">
          <!-- Navbar Search -->
          <li class="nav-item">
            <a class="nav-link" data-widget="navbar-search" href="#" role="button">
              <i class="fas fa-search"></i>
            </a>
            <div class="navbar-search-block">
              <form class="form-inline">
                <div class="input-group input-group-sm">
                  <input class="form-control form-control-navbar" type="search" placeholder="Search"
                    aria-label="Search" />
                  <div class="input-group-append">
                    <button class="btn btn-navbar" type="submit">
                      <i class="fas fa-search"></i>
                    </button>
                    <button class="btn btn-navbar" type="button" data-widget="navbar-search">
                      <i class="fas fa-times"></i>
                    </button>
                  </div>
                </div>
              </form>
            </div>
          </li>

          <!-- Notifications Dropdown Menu -->
          <li class="nav-item dropdown">
            <a class="nav-link" data-toggle="dropdown" href="#">
              <i class="far fa-bell"></i>
              <span class="badge badge-warning navbar-badge">15</span>
            </a>
            <div class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
              <span class="dropdown-item dropdown-header">15 Notifications</span>
              <div class="dropdown-divider"></div>
              <a href="#" class="dropdown-item">
                <i class="fas fa-envelope mr-2"></i> 4 new messages
                <span class="float-right text-muted text-sm">3 mins</span>
              </a>
              <div class="dropdown-divider"></div>
              <a href="#" class="dropdown-item">
                <i class="fas fa-users mr-2"></i> 8 friend requests
                <span class="float-right text-muted text-sm">12 hours</span>
              </a>
              <div class="dropdown-divider"></div>
              <a href="#" class="dropdown-item">
                <i class="fas fa-file mr-2"></i> 3 new reports
                <span class="float-right text-muted text-sm">2 days</span>
              </a>
              <div class="dropdown-divider"></div>
              <a href="#" class="dropdown-item dropdown-footer">See All Notifications</a>
            </div>
          </li>
        </ul>
      </nav>
      <!-- /.navbar -->

      <!-- Sidebar Container -->
      <aside class="main-sidebar sidebar-dark-primary elevation-4">
        <!-- User Logo -->
        <a href="#" class="brand-link" data-toggle="dropdown">
          <img src="resources/adminlte/dist/img/user2-160x160.jpg" alt="User Logo"
            class="brand-image img-circle elevation-3" style="opacity: 0.8" />
          <span class="brand-text font-weight-light">${account.staff.fullName}</span>
        </a>
        <div class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
          <a href="admin/account/logout" class="dropdown-item dropdown-footer">Thoát</a>
        </div>

        <!-- Sidebar -->
        <div class="sidebar">
          <!-- Sidebar Menu -->
          <nav class="mt-2">
            <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
              <!-- Add icons to the links using the .nav-icon class
               with font-awesome or any other icon font library -->
              <li class="nav-header">HÀNG HÓA</li>
              <li class="nav-item">
                <a class="nav-link" id="commodity">
                  <i class="nav-icon fas fa-ellipsis-h"></i>
                  <p>Mặt hàng</p>
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" id="category">
                  <i class="nav-icon fas fa-file"></i>
                  <p>Loại hàng</p>
                </a>
              </li>
            </ul>
          </nav>
          <!-- /.sidebar-menu -->
        </div>
        <!-- /.sidebar -->
      </aside>
      <!-- ./sidebar-container -->

      <!-- Content Wrapper. Contains page content -->
      <div class="content-wrapper" id="content-wrapper">
        <!-- Tong quan hang hoa -->
        <!-- ./Tong quan hang hoa -->

        <!-- Mat hang -->
        <div hidden id="commodity-content" class="row" style="margin-left: 0; margin-right: 0">
            <div class="col-md-7">
                <div class="card">
                    <div class="card-header">Thông tin mặt hàng</div>
                    <div class="card-body">
                        <input type="text" class="form-control" id="commodity-id" hidden>
                        <label for="commodity-name" class="form-label">Tên mặt hàng</label>
                        <div class="input-group mb-3">
                            <input type="text" class="form-control" id="commodity-name" autocomplete="off">
                        </div>
                        <label for="#" class="form-label">Các loại hàng</label>
                        <div class="input-group mb-3">
                            <select id="category-list" style="width: 100%;" multiple></select>
                        </div>
                        <div class="input-group mb-3 row">
                            <div class="col-md-3">
                                <a class="btn btn-primary" id="btn-commodity-save" style="width: 120px;">
                                    Lưu thông tin
                                </a>
                            </div>
                            <div class="col-md-3">
                                <a class="btn btn-danger" id="btn-commodity-cancel" style="width: 120px;" hidden>
                                    Hủy
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-5">
                <table id="tbCommodity">
                    <thead>
                      <tr>
                        <th>Tên</th>
                        <th>Ngày tạo</th>
                        <th>Chức năng</th>
                      </tr>
                    </thead>
                </table>
            </div>
        </div>
        <!-- ./Mat hang -->

        <!-- Loai hang -->
        <div hidden id="category-content" class="row" style="margin-left: 0; margin-right: 0">
            <div class="col-md-7">
                <div class="card">
                    <div class="card-header">Thông tin loại hàng</div>
                    <div class="card-body">
                        <input type="text" class="form-control" id="category-id" hidden>
                        <label for="category-name" class="form-label">Tên loại hàng</label>
                        <div class="input-group mb-3">
                            <input type="text" class="form-control" id="category-name" autocomplete="off">
                        </div>
                        <label for="#" class="form-label">Ảnh bìa</label>
                        <div class="input-group mb-3">
                            <input type="file" class="form-control" id="category-coverphoto">
                        </div>
                        <label for="category-description" class="form-label">Mô tả loại hàng</label>
                        <div class="main-editor-container mb-3">
                            <div class="editor-container editor-container_classic-editor" id="editor-container">
                                <div class="editor-container__editor">
                                    <textarea id="category-description" rows="10" cols="50"></textarea>
                                </div>
                            </div>
                        </div>
                        <div class="input-group mb-3 row">
                            <div class="col-md-3">
                                <a class="btn btn-primary" id="btn-category-save" style="width: 120px;">
                                    Lưu thông tin
                                </a>
                            </div>
                            <div class="col-md-3">
                                <a class="btn btn-danger" id="btn-category-cancel" style="width: 120px;" hidden>
                                    Hủy
                                </a>
                            </div>
                        </div>
                    </div>
                    <div class="card-footer">
                        <div class="row">
                            <div class="col-md-3">
                                <a class="btn btn-success" id="btn-category-browse-image" style="width: 120px;">
                                    Duyệt ảnh
                                </a>
                            </div>
                            <div class="col-md-3">
                                <a class="btn btn-dark" id="btn-category-upload-image" style="width: 120px;">
                                    Tải ảnh
                                </a>
                            </div>
                            <div class="col-md-6">
                                <input type="file" class="form-control" id="category-multiple-photo"
                                       style="width: 250px;" multiple hidden>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-5">
              <table id="tbCategory">
                <thead>
                  <tr>
                    <th>Tên</th>
                    <th>Ngày tạo</th>
                    <th>Chức năng</th>
                  </tr>
                </thead>
              </table>
            </div>
        </div>
        <!-- ./Loai hang -->
      </div>
      <!-- /.content-wrapper -->

      <!-- Footer -->
      <footer class="main-footer">
        <strong>Copyright &copy; 2024-2025</strong>
        <div class="float-right d-none d-sm-inline-block">
          <b>Version</b> 1.0.1
        </div>
      </footer>
      <!-- /.footer -->
    </div>
    <!-- ./wrapper -->

    <!-- jQuery -->
    <script src="resources/adminlte/plugins/jquery/jquery.min.js"></script>
    <!-- jQuery UI 1.11.4 -->
    <script src="resources/adminlte/plugins/jquery-ui/jquery-ui.min.js"></script>
    <!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
    <script>
      $.widget.bridge("uibutton", $.ui.button);
    </script>
    <!-- Bootstrap 4 -->
    <script src="resources/adminlte/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
    <!-- ChartJS -->
    <script src="resources/adminlte/plugins/chart.js/Chart.min.js"></script>
    <!-- Sparkline -->
    <script src="resources/adminlte/plugins/sparklines/sparkline.js"></script>
    <!-- JQVMap -->
    <script src="resources/adminlte/plugins/jqvmap/jquery.vmap.min.js"></script>
    <script src="resources/adminlte/plugins/jqvmap/maps/jquery.vmap.usa.js"></script>
    <!-- jQuery Knob Chart -->
    <script src="resources/adminlte/plugins/jquery-knob/jquery.knob.min.js"></script>
    <!-- daterangepicker -->
    <script src="resources/adminlte/plugins/moment/moment.min.js"></script>
    <script src="resources/adminlte/plugins/daterangepicker/daterangepicker.js"></script>
    <!-- Tempusdominus Bootstrap 4 -->
    <script src="resources/adminlte/plugins/tempusdominus-bootstrap-4/js/tempusdominus-bootstrap-4.min.js"></script>
    <!-- Summernote -->
    <script src="resources/adminlte/plugins/summernote/summernote-bs4.min.js"></script>
    <!-- overlayScrollbars -->
    <script src="resources/adminlte/plugins/overlayScrollbars/js/jquery.overlayScrollbars.min.js"></script>
    <!-- AdminLTE App -->
    <script src="resources/adminlte/dist/js/adminlte.js"></script>
    <!-- AdminLTE for demo purposes -->
    <script src="resources/adminlte/dist/js/demo.js"></script>
    <!-- AdminLTE dashboard demo (This is only for demo purposes) -->
    <script src="resources/adminlte/dist/js/pages/dashboard.js"></script>
    <!-- CKEditor -->
    <script type="importmap">
        {
            "imports": {
                "ckeditor5": "./resources/ckeditor5/ckeditor5.js",
                "ckeditor5/": "./resources/ckeditor5/"
            }
        }
    </script>
    <!-- DataTables -->
    <script src="resources/datatables/datatables.min.js"></script>
    <!-- Select2 -->
    <script src="resources/select2/select2.min.js"></script>
    <!-- Custom -->
    <script type="module" src="resources/js/admin.dashboard.js"></script>
    <script type="module" src="resources/js/category-management.js"></script>
    <script type="module" src="resources/js/commodity-management.js"></script>
  </body>
</html>
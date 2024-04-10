$(function () {
  $('#example1').DataTable().destroy();
  $('#example1').DataTable({
    "responsive": true,
    "lengthChange": false,
    "autoWidth": false,
    "buttons": ["copy", "csv", "excel", "pdf", "print", "colvis"],
    "order": [], // Disable initial sorting
    "language": {
      "search": "_INPUT_", // Customize the search input
      "searchPlaceholder": "Search...", // Placeholder for the search input
      "paginate": {
        "previous": "<",
        "next": ">"
      }
    }
  }).buttons().container().appendTo('#example1_wrapper .col-md-6:eq(0)');

  $('#example2').DataTable().destroy();
  $('#example2').DataTable({
    "paging": true,
    "lengthChange": false,
    "searching": true,
    "ordering": true,
    "info": true,
    "autoWidth": false,
    "responsive": true,
    "language": {
      "paginate": {
        "previous": "<",
        "next": ">"
      }
    }
  });
});

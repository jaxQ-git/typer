
$('#deleteModal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget) // Button that triggered the modal
    var link = button.data('link')
    // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
    // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
    var modal = $(this)
    console.log(link);
    modal.find('#deleteLink').attr('href',link)
    modal.find('#contentModal').text('Czy na pewno chcesz usunąć ten element?')
})

$(document).ready(function(){
    $("#mySearch").on("keyup", function() {
        var value = $(this).val().toLowerCase();
        $("#searchTable tr").filter(function() {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });
});

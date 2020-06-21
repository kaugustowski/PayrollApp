function updateLink() {
    const $type = $("#type").val();
    const $year = $("#year").val();
    const $month = $("#month").val();
    let link = "/payrollApp/salary/" + $type + "/" + $year + "/" + $month;
    console.log(link);
    // $("#form").attr("action",link);
    let $form = $("#form")
    $form.attr("action", link).attr("method", "POST");
    $("#link").attr("href", link);

    const csrf = document.createElement('input');
    csrf.setAttribute("type", "hidden");
    csrf.setAttribute("name", "_token");
    csrf.setAttribute("value", "{{csrf_field()}}")
    let form = document.getElementById("form");
    form.appendChild(csrf);
}

function selectValues() {

    const start = new Date().getFullYear();
    const end = new Date().getFullYear() - 5;
    let options = "";
    for (let year = start; year >= end; year--) {
        options += "<option value=\"" + year + "\">" + year + "</option>";
    }
    document.getElementById("year").innerHTML = options;

    const start2 = 1;
    const end2 = 12;
    let options2 = "";
    for (let month = start2; month <= end2; month++) {
        options2 += "<option value=\"" + month + "\">" + month + "</option>";
    }
    document.getElementById("month").innerHTML = options2;

}


function filterBySearchbar() {

    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById("search");
    filter = input.value.toUpperCase();
    table = document.getElementById("myTable");
    tr = table.getElementsByTagName("tr");

    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[0];
        if (td) {
            txtValue = td.textContent || td.innerText;
            if (txtValue.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}
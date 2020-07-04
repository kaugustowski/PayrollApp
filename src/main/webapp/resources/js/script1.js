function updateLink() {
    const $type = $("#type").val();
    const $year = $("#year").val();
    const $month = $("#month").val();
    let link = "/payrollApp/salary/" + $type + "/" + $year + "/" + $month;
    let $form = $("#form")
    $form.attr("action", link).attr("method", "POST");
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

    updateLink()
}

//
// function filterBySearchbar() {
//
//     let input, filter, table, tr, td, i, txtValue;
//     input = document.getElementById("search");
//     filter = input.value.toUpperCase();
//     table = document.getElementById("searchTable");
//     tr = table.getElementsByTagName("tr");
//
//     for (i = 0; i < tr.length; i++) {
//         //for (let j = 0; j < 4; i++) {
//             td = tr[i].getElementsByTagName("td")[1];
//             if (td) {
//                 txtValue = td.textContent || td.innerText;
//                 if (txtValue.toUpperCase().indexOf(filter) > -1) {
//                     tr[i].style.display = "";
//                 } else {
//                     tr[i].style.display = "none";
//                 }
//             }
//        // }
//     }
// }

function filterBySearchbar() {
    const input = document.getElementById("search");
    const filter = input.value.toUpperCase();
    const table = document.getElementById("searchTable");
    const tbody = table.getElementsByTagName("tbody");
    const tr = tbody.getElementsByTagName("tr");
    const tds = tr.getElementsByTagName("td");

    for (let i = 0; i < tr.length; i++) {
        const firstCol = tds[0].textContent.toUpperCase();
        const secondCol = tds[1].textContent.toUpperCase();
        const thirdCol = tds[2].textContent.toUpperCase();
        if (firstCol.indexOf(filter) > -1 || secondCol.indexOf(filter) > -1 || thirdCol.indexOf(filter) > -1) {
            tr[i].style.display = "";
        } else {
            tr[i].style.display = "none";
        }
    }
}

(function searchFilter() {

    const searchInput = document.getElementById("search");
    const targetRows = Array.from(document.querySelectorAll("#searchTable tbody tr"));
    const rowsWithAppropriateCellsString = targetRows.map(row => {
        const rowCells = Array.from(row.children);
        const MAX_CELLS = 3;
        let appropriateCellsString = "";
        for (let i = 0; i < MAX_CELLS; i++) {
            appropriateCellsString += rowCells[i].textContent.toLowerCase();
        }
        return {
            row,
            appropriateCellsString,
            isVisible: true
        }
    })

    const checkRowsVisibility = (rowObjArr, stringToCheck) => rowObjArr.forEach(rowObj => {
        const isVisible = rowObj.appropriateCellsString.indexOf(stringToCheck) !== -1;
        rowObj.isVisible = isVisible;
    });
    const toggleRowsVisibility = rowObjArr => rowObjArr.forEach(rowObj => rowObj.row.style.setProperty("display", rowObj.isVisible ? "table-row" : "none", "important"));

    searchInput.addEventListener("keyup", ({target: {value: inputText}}) => {
        checkRowsVisibility(rowsWithAppropriateCellsString, inputText.toLowerCase());
        toggleRowsVisibility(rowsWithAppropriateCellsString);
    })

})()


function goBack() {
    window.history.go(-1);
}

var idCourse = [];
var period = [
    { id: 1, name: "Manhã" }, 
    { id: 2, name: "Tarde" }, 
    { id: 3, name: "Noite" } 
];
var students = [];

// Carregar as informações ao carregar a página
loadIdCourses();
loadStudents();

// Função para carregar os cursos
function loadIdCourses() {
    $.ajax({
        url: "http://localhost:8080/courses",
        type: "GET",
        async: false, // Chamada síncrona
        success: (response) => {
            idCourse = response;  // Agora a variável idCourse está corretamente preenchida
            for (var cat of idCourse) {
                document.getElementById("SelectOption").innerHTML += `<option value="${cat.id}">${cat.name}</option>`;
            }
        }
    });
}

// Função para carregar os alunos
function loadStudents() {
    $.getJSON("http://localhost:8080/students", (response) => {
        students = response;
        for (let stud of students) {
            AddNewRow(stud);
        }
    });
}

// Função para adicionar uma nova linha na tabela de alunos
function AddNewRow(Item) {
    var table = document.getElementById("StudentsTable");
    var newRow = table.insertRow();

    // Carregar o ID do aluno
    var idNode = document.createTextNode(Item.id);
    newRow.insertCell().appendChild(idNode);

    // Carregar o nome do aluno
    var nameNode = document.createTextNode(Item.name);
    newRow.insertCell().appendChild(nameNode);

    // Carregar o e-mail do aluno
    var emailNode = document.createTextNode(Item.email);
    var cell = newRow.insertCell();
    cell.className = "d-none d-md-table-cell";
    cell.appendChild(emailNode);

    // Carregar o telefone do aluno
    var phoneNode = document.createTextNode(Item.phone);
    cell = newRow.insertCell();
    cell.className = "d-none d-md-table-cell";
    cell.appendChild(phoneNode);
    
    // Carregar o curso do aluno
    var idCourseNode = document.createTextNode((idCourse[Item.idCourse - 1].name));
    cell = newRow.insertCell();
    cell.className = "d-none d-md-table-cell";
    cell.appendChild(idCourseNode);

    // Carregar o turno (periodo) do aluno
    var periodNode = document.createTextNode((period[Item.period - 1].name));
    cell = newRow.insertCell();
    cell.className = "d-none d-md-table-cell";
    cell.appendChild(periodNode);
}

// Função para salvar um novo aluno
function save() {
    var shiftSelected = document.querySelector('input[name="RadiusShifts"]:checked').value;

    var item = {
        id: students.length + 1,
        name: document.getElementById("InputName").value,
        email: document.getElementById("InputEmail").value,
        phone: document.getElementById("InputTelefone").value,
        idCourse: parseFloat(document.getElementById('SelectOption').value),
        period: parseFloat(shiftSelected)
    };

    r

    AddNewRow(item);
    students.push(item);

    document.getElementById('FormStudent').reset();
}
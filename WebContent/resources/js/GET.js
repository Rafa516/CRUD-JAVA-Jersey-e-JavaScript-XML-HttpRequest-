//listando Funcionários READ
function listaFuncionarios() {
    const xhttp = new XMLHttpRequest();
    xhttp.open("GET", "http://localhost:8080/funcionarios/rest/funcionarios/listar");
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            console.log(this.responseText);
            var trHTML = '';
            const funcionarios = JSON.parse(this.responseText);
          
            for (let funcionario of funcionarios) {
                funcionario['aniversario'] =  new Date(funcionario['aniversario']);
                funcionario['aniversario'] = new Intl.DateTimeFormat('pt-BR').format(funcionario['aniversario'])
                trHTML += '<tr><center>';
                trHTML += '<td><center>' + funcionario['id_funcionario'] + '</center></td>';
                trHTML += '<td><center>' + funcionario['foto'] + '</center></td>';
                trHTML += '<td><center>' + funcionario['nome'] + '</center></td>';
                trHTML += '<td><center>' + funcionario['setor'] + '</center></td>';
                trHTML += '<td><center>' + funcionario['aniversario'] + '</center></td>';
                trHTML += '<td><center><button class="button buttonUpdate" onclick="alertEditarFuncionario(' + funcionario['id_funcionario'] + ')">Editar</button>';
                trHTML += '&nbsp;<button class="button buttonDelete" onclick="deleteFuncionario(' + funcionario['id_funcionario'] + ')">Excluir</button></center></td>';
                trHTML += "</tr>";
            }
            document.getElementById("tabela").innerHTML = trHTML;
        }
    };

}

//listando Setores READ
function listaSetores() {
    const xhttp = new XMLHttpRequest();
    xhttp.open("GET", "http://localhost:8080/funcionarios/rest/setores/listar");
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            console.log(this.responseText);
            var trHTML = '';
            const setores = JSON.parse(this.responseText);
            for (let setor of setores) {
                
                trHTML += '<tr><center>';
                trHTML += '<td><center>' + setor['id_setor'] + '</center></td>';
                trHTML += '<td><center>' + setor['nome_setor'] + '</center></td>';
                trHTML += '<td><center><button class="button buttonUpdate" onclick="alertEditarSetor(' + setor['id_setor'] + ')">Editar</button>';
                trHTML += '&nbsp;<button class="button buttonDelete" onclick="deleteSetor(' + setor['id_setor'] + ')">Excluir</button></center></td>';
                trHTML += "</tr>";
            }
            document.getElementById("tabela").innerHTML = trHTML;
        }
    };

}

//Select Setores
function selectSetor() {
    const xhttp = new XMLHttpRequest();
    xhttp.open("GET", "http://localhost:8080/funcionarios/rest/setores/listar");
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            console.log(this.responseText);
            var selectHTML = '';
            const setores = JSON.parse(this.responseText);
            for (let setor of setores) {

                selectHTML += '<option value="' + setor['id_setor'] + '">' + setor['nome_setor'] + '</option>';

            }
            document.getElementById("select_setores").innerHTML = selectHTML;
        }
    };

}


// Alert para alterar dados do Funcionário selecionado 

function alertEditarFuncionario(id_funcionario) {
    console.log(id_funcionario);
    const xhttp = new XMLHttpRequest();
    xhttp.open("GET", "http://localhost:8080/funcionarios/rest/funcionarios/listar/" + id_funcionario);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            const funcionarios = JSON.parse(this.responseText);
            const funcionario = funcionarios['0'];
            console.log(funcionario);
            selectSetor()
            Swal.fire({
                title: 'Alterar dados',
                html:
                    '<input id="id_funcionario" type="hidden" value="' + funcionario['id_funcionario'] + '">' +
                    '<b>Nome</b><br><input id="nome" class="swal2-input" value="' + funcionario['nome'] + '" >' +
                    '<br><br><b>Setor</b><br><select id="select_setores" class="swal2-input" >' +
                    '<br><br><b>Aniversário<b><br> <input type="date" id="aniversario" class="swal2-input"  value="' + funcionario['aniversario'] + '">' +
                    '<br><br><b>Foto</b><br><input  value="' + funcionario['foto'] + '" type="file"  id="foto" class="swal2-file">',
                focusConfirm: false,
                preConfirm: () => {
                    updateFuncionario();
                }
            })
        }
    };
}

//alert para alterar dados do setor selecionado 

function alertEditarSetor(id_setor) {
    console.log(id_setor);
    const xhttp = new XMLHttpRequest();
    xhttp.open("GET", "http://localhost:8080/funcionarios/rest/setores/listar/" + id_setor);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            const setores = JSON.parse(this.responseText);
            const setor = setores['0'];
            console.log(setor);
            Swal.fire({
                title: 'Alterar dados',
                html:
                    '<input id="id_setor" type="hidden" value="' + setor['id_setor'] + '">' +
                    '<input id="nome_setor" class="swal2-input"  value="' + setor['nome_setor'] + '">',
                focusConfirm: false,
                preConfirm: () => {
                    updateSetor();
                }
            })
        }
    };
}

//Buscando setores LIKE %%
function buscarSetor() {
    const nome_setor = document.getElementById("busca_nome").value;
    console.log(nome_setor);
    const xhttp = new XMLHttpRequest();
    xhttp.open("GET", "http://localhost:8080/funcionarios/rest/setores/buscar/"+nome_setor);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (this.status == 200) {
            console.log(this.responseText);
            var trHTML = '';
            const setores = JSON.parse(this.responseText);
            for (let setor of setores) {
                trHTML += '<tr><center>';
                trHTML += '<td><center>' + setor['id_setor'] + '</center></td>';
                trHTML += '<td><center>' + setor['nome_setor'] + '</center></td>';
                trHTML += '<td><center><button class="button buttonUpdate" onclick="alertEditarSetor(' + setor['id_setor'] + ')">Editar</button>';
                trHTML += '&nbsp;<button class="button buttonDelete" onclick="deleteSetor(' + setor['id_setor'] + ')">Excluir</button></center></td>';
                trHTML += "</tr>";
            }
            document.getElementById("tabela").innerHTML = trHTML;
           
        }else if (this.status == 404 ){
            Swal.fire({
                position: 'top',
                icon: 'warning',
                title: 'Busca Vazia',
                showConfirmButton: false,
              })
              setTimeout(function() {
                window.location.reload(1);
              }, 1000);
        }
        else {
            Swal.fire({
                position: 'top',
                icon: 'warning',
                title: 'Nenhum resultado para sua busca',
                showConfirmButton: false,
              })
              setTimeout(function() {
                window.location.reload(1);
              }, 1000); 
        }

        
    };

}

//Buscando Funcionarios LIKE %%
function buscarFuncionario() {
    const nome = document.getElementById("busca_nome").value;
    console.log(nome);
    const xhttp = new XMLHttpRequest();
    xhttp.open("GET", "http://localhost:8080/funcionarios/rest/funcionarios/buscar/"+ nome);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (this.status == 200) {
            console.log(this.responseText);
            var trHTML = '';
            const funcionarios = JSON.parse(this.responseText);
          
            for (let funcionario of funcionarios) {
                funcionario['aniversario'] =  new Date(funcionario['aniversario']);
                funcionario['aniversario'] = new Intl.DateTimeFormat('pt-BR').format(funcionario['aniversario'])
                trHTML += '<tr><center>';
                trHTML += '<td><center>' + funcionario['id_funcionario'] + '</center></td>';
                trHTML += '<td><center>' + funcionario['foto'] + '</center></td>';
                trHTML += '<td><center>' + funcionario['nome'] + '</center></td>';
                trHTML += '<td><center>' + funcionario['setor'] + '</center></td>';
                trHTML += '<td><center>' + funcionario['aniversario'] + '</center></td>';
                trHTML += '<td><center><button class="button buttonUpdate" onclick="alertEditarFuncionario(' + funcionario['id_funcionario'] + ')">Editar</button>';
                trHTML += '&nbsp;<button class="button buttonDelete" onclick="deleteFuncionario(' + funcionario['id_funcionario'] + ')">Excluir</button></center></td>';
                trHTML += "</tr>";
            }
          
            document.getElementById("tabela").innerHTML = trHTML;
         
        }else if (this.status == 404 ){
            Swal.fire({
                position: 'top',
                icon: 'warning',
                title: 'Busca Vazia',
                showConfirmButton: false,
              })
              setTimeout(function() {
                window.location.reload(1);
              }, 1000);
        }
        else {
            Swal.fire({
                position: 'top',
                icon: 'warning',
                title: 'Nenhum resultado para sua busca',
                showConfirmButton: false,
              })
              setTimeout(function() {
                window.location.reload(1);
              }, 1000); 
        }

        
    };

}









//Cadastrar Funcionários CREATE

function alertCreateFuncionario() {
    selectSetor();
    Swal.fire({
        title: 'Cadastrar Funcionário',
        html:

            '<b>Nome</b><br><input id="nome" class="swal2-input" placeholder="Digite seu Nome" required>' +
            '<br><br><b>Setor</b><br><select id="select_setores" class="swal2-input" ></select>' +
            '<br><br><b>Aniversário<b><br> <input type="date" id="aniversario" class="swal2-input">' +
            '<br><br><b>Foto</b><br><input type="file"  id="foto" class="swal2-file" >',
        focusConfirm: true,
        preConfirm: () => {
            createFuncionario();              
        }
    })
     
}

function createFuncionario() {
    const nome = document.getElementById("nome").value;
    const id_setor = document.getElementById("select_setores").value;
    const aniversario = document.getElementById("aniversario").value;
    const foto = document.getElementById("foto").value;
    const xhttp = new XMLHttpRequest();
    xhttp.open("POST", "http://localhost:8080/funcionarios/rest/funcionarios/salvar");
    xhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xhttp.send(JSON.stringify({
        "nome": nome, "id_setor": id_setor, "aniversario": aniversario, "foto": foto
    }));
    Swal.fire({
        position: 'top',
        icon: 'success',
        title: 'Funcionario '+ nome + ' cadastrado com Sucesso!!!',
        showConfirmButton: false,
      })
      setTimeout(function() {
        window.location.reload(1);
      }, 1500);
}

//Cadastrar Setores CREATE


function alertCreateSetor() {
    Swal.fire({

        title: 'Cadastrar Setor',
        html:
            '<b>Nome</b><br><input id="nome_setor" class="swal2-input" placeholder="Digite o nome do setor">',
        focusConfirm: false,
        preConfirm: () => {
            createSetor();
        }
    })
}

function createSetor() {
    const nome_setor = document.getElementById("nome_setor").value;
    const xhttp = new XMLHttpRequest();
    xhttp.open("POST", "http://localhost:8080/funcionarios/rest/setores/salvar");
    xhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xhttp.send(JSON.stringify({
        "nome_setor": nome_setor
    }));
    Swal.fire({
        position: 'top',
        icon: 'success',
        title: 'Setor '+ nome_setor + ' cadastrado com Sucesso!!!',
        showConfirmButton: false,
      })
      setTimeout(function() {
        window.location.reload(1);
      }, 1500);

}



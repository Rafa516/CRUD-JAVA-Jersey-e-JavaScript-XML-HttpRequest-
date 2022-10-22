//Alterar Funcionarios

function updateFuncionario() {
 
    const id_funcionario = document.getElementById("id_funcionario").value;
    const nome = document.getElementById("nome").value;
    const id_setor = document.getElementById("select_setores").value;
    const aniversario = document.getElementById("aniversario").value;
    const foto = document.getElementById("foto").value
    const xhttp = new XMLHttpRequest();
    xhttp.open("PUT", "http://localhost:8080/funcionarios/rest/funcionarios/editar/" + id_funcionario);
    xhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xhttp.send(JSON.stringify({
        "id_funcionario": id_funcionario, "nome": nome, "id_setor": id_setor, "aniversario": aniversario, "foto": foto
    }));
    Swal.fire({
        position: 'top',
        icon: 'success',
        title: 'Dados Aterados!!',
        showConfirmButton: false,
      })
      setTimeout(function () {
        window.location.reload(1);
      }, 1500);
    
}


//Alterar Setores

function updateSetor() {
    const id_setor = document.getElementById("id_setor").value;
    const nome_setor = document.getElementById("nome_setor").value;
    const xhttp = new XMLHttpRequest();
    xhttp.open("PUT", "http://localhost:8080/funcionarios/rest/setores/editar/" + id_setor);
    xhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xhttp.send(JSON.stringify({
        "id_setor": id_setor, "nome_setor": nome_setor
    }));
    Swal.fire({
        position: 'top',
        icon: 'success',
        title: 'Dados Alterados !!',
        showConfirmButton: false,
      })
      setTimeout(function () {
        window.location.reload(1);
      }, 1500);
}







//Excluindo Funcionários DELETE
function deleteFuncionario(id_funcionario) {

  swal.fire({
    title: "Excluir Funcionário",
    text: "Tem certeza que deseja excluir o Funcionário n°" + id_funcionario + " ?",
    icon: "warning",
    showCancelButton: true,
    confirmButtonText: 'Excluir',
    cancelButtonText: 'Cancelar',
  }).then((result) => {
      if (result.isConfirmed) {
        const xhttp = new XMLHttpRequest();
        xhttp.open("DELETE", "http://localhost:8080/funcionarios/rest/funcionarios/excluir/" + id_funcionario);
        xhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
        xhttp.send(JSON.stringify({
          "id_funcionario": id_funcionario
        }));
        Swal.fire({
          position: 'top',
          icon: 'success',
          title: 'Funcionário excluído com Sucesso!!!',
          showConfirmButton: false,
        })
        setTimeout(function () {
          window.location.reload(1);
        }, 1500);
      } else {
        Swal.fire({
          position: 'top',
          title: 'Cancelou operação',
          showConfirmButton: false,
        })
        setTimeout(function () {
          window.location.reload(1);
        }, 1000);
      }
    });
}

//Excluindo Setores DELETE

function deleteSetor(id_setor) {
  swal.fire({
    title: "Excluir setor",
    text: "Tem certeza que deseja excluir o setor " + id_setor + " ?",
    icon: "warning",
    showCancelButton: true,
    confirmButtonText: 'Excluir',
    cancelButtonText: 'Cancelar',
  }).then((result) => {
      if (result.isConfirmed) {
        const xhttp = new XMLHttpRequest();
        xhttp.open("DELETE", "http://localhost:8080/funcionarios/rest/setores/excluir/" + id_setor);
        xhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
        xhttp.send(JSON.stringify({
          "id_setor": id_setor
        }));
        Swal.fire({
          position: 'top',
          icon: 'success',
          title: 'Setor excluído com Sucesso!!!',
          showConfirmButton: false,
        })
        setTimeout(function () {
          window.location.reload(1);
        }, 1500);
      } else {
        Swal.fire({
          position: 'top',
          title: 'Cancelou operação',
          showConfirmButton: false,
        })
        setTimeout(function () {
          window.location.reload(1);
        }, 1000);
      }
    });
}
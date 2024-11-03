document.addEventListener('DOMContentLoaded', function () {
    const saveButton = document.querySelector('.botao-modal');

    saveButton.addEventListener('click', function (event) {
        event.preventDefault();

        const nomeCompleto = document.querySelector('#nomeCompletoModal').value;
        const email = document.querySelector('#emailModal').value;
        const dataNasc = document.querySelector('#data-nasc-modal').value;
        const senha = document.querySelector('#senhaModal').value;
        const genero = document.querySelector('#generoModal').value;

        document.getElementById('nomeCompleto').value = nomeCompleto;
        document.getElementById('emailCadastro').value = email;
        document.getElementById('data-nasc-cadastro').value = dataNasc;
        document.getElementById('senhaCadastro').value = senha;
        document.getElementById('generoCadastro').value = genero;

        const modal = bootstrap.Modal.getInstance(document.getElementById('exampleModal'));
        modal.hide();
    });
});       
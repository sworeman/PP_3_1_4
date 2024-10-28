document.addEventListener('DOMContentLoaded', () => {
    const editUserModal = document.getElementById('editUserModal');

    if (editUserModal) {
        editUserModal.addEventListener('show.bs.modal', function (event) {
            const button = event.relatedTarget;
            const id = button.getAttribute('data-id-th');
            const firstName = button.getAttribute('data-firstname-th');
            const lastName = button.getAttribute('data-lastname-th');
            const age = button.getAttribute('data-age-th');
            const username = button.getAttribute('data-username-th');
            const roles = button.getAttribute('data-roles-th');

            const modal = this;
            modal.querySelector('#editUserId').value = id;
            modal.querySelector('#editFirstName').value = firstName;
            modal.querySelector('#editLastName').value = lastName;
            modal.querySelector('#editAge').value = age;
            modal.querySelector('#editUsername').value = username;
            modal.querySelector('#editRoles').value = roles;
        });
    }
    const editUserForm = document.getElementById('editUserForm');
    if (editUserForm) {
        editUserForm.addEventListener('submit', function (event) {
            event.preventDefault();

            const formData = new FormData(editUserForm);
            const id = formData.get('id');

            fetch(`/admin/update_user?id=${id}`, {
                method: 'POST',
                body: formData
            }).then(response => {
                if (response.ok) {
                    const modal = bootstrap.Modal.getInstance(editUserModal);
                    modal.hide();
                    window.location.reload();
                } else {
                    alert('Failed to update user');
                }
            }).catch(error => {
                console.error('Error:', error);
            });
        });
    }
});

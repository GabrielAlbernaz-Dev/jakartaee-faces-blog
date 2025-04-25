(function(){
    document.addEventListener('DOMContentLoaded', function() {
        const profileContainer = document.querySelector('.profile-container');
        const dropdownItems = profileContainer.querySelectorAll('.dropdown-item');
        
        profileContainer.addEventListener('click', e => {
            profileContainer.classList.toggle('active');
            e.stopPropagation();
        });

        dropdownItems.forEach(item => {
            item.addEventListener('click', () => {
                const actionBtn = item.querySelector('.action, .action-button');
                console.log(88,actionBtn);
                actionBtn?.click();
            });
        });
        
        document.addEventListener('click', () => {
            profileContainer.classList.remove('active');
        });
    });
})();
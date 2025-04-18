(function(){
    document.addEventListener('DOMContentLoaded', function() {
        const profileContainer = document.querySelector('.profile-container');
        const logoutContainers = document.querySelectorAll('.logout-container');
        const logoutBtn = document.querySelector('.logout');
        
        profileContainer.addEventListener('click', (event) => {
            profileContainer.classList.toggle('active');
            event.stopPropagation();
        });

        logoutContainers.forEach(logoutContainer => {
            logoutContainer.addEventListener('click', () => {
                logoutBtn?.click();
            });
        });
        
        document.addEventListener('click', () => {
            profileContainer.classList.remove('active');
        });
    });
})();
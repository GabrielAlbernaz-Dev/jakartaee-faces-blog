(function(){
    document.addEventListener('DOMContentLoaded', function() {
        const profileContainer = document.querySelector('.profile-container');
        
        profileContainer.addEventListener('click', (event) => {
            profileContainer.classList.toggle('active');
            event.stopPropagation();
        });
        
        document.addEventListener('click', () => {
            profileContainer.classList.remove('active');
        });
    });
})();
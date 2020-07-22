function loadRepos() {

   let resElement = document.getElementById('res');

   let url = 'https://api.github.com/repos/testnakov/test-nakov-repo/issues';

   let xmlHttpRequest = new XMLHttpRequest();

   xmlHttpRequest.addEventListener('readystatechange', () => {

      if (xmlHttpRequest.readyState === 4) {

         if (xmlHttpRequest.status === 200) {

            resElement.textContent = xmlHttpRequest.responseText;

         } else if (xmlHttpRequest.status === 404) {

            console.warn('Not found');

         }

      }

   });

   xmlHttpRequest.open('GET', url);
   xmlHttpRequest.send();

}
document.getElementById('search-button').addEventListener('click', function() {
    const topic = document.getElementById('search-input').value;
    if (topic.trim() === '') {
        alert('Please enter a topic');
        return;
    }

    document.getElementById('loading').classList.remove('hidden');
    document.getElementById('results').classList.add('hidden');
    document.getElementById('results').innerHTML = '';

    fetch(`http://localhost:8080/api/article?subject=${encodeURIComponent(topic)}`)
        .then(response => response.json())
        .then(data => {
            document.getElementById('loading').classList.add('hidden');
            if (data.length === 0) {
                document.getElementById('results').innerHTML = '<p>No articles found</p>';
                document.getElementById('results').classList.remove('hidden');
                return;
            }

            let resultsHTML = '';
            data.forEach(article => {
                resultsHTML += `
          <div class="article">
            <div class="article-content">
              <h2>${article.title}</h2>
              <p><strong>Author:</strong> ${article.author}</p>
              <p><strong>Date:</strong> ${article.date}</p>
            </div>
            <div class="article-buttons">
              <button class="google-scholar-btn" onclick="searchGoogleScholar('${article.title}')">Google Scholar'da Bul</button>
              <button class="save-favorite-btn" onclick="saveFavorite('${article.title}', '${article.author}', '${article.date}')">Favorilere Kaydet</button>
            </div>
          </div>
        `;
            });

            document.getElementById('results').innerHTML = resultsHTML;
            document.getElementById('results').classList.remove('hidden');
        })
        .catch(error => {
            console.error('Error:', error);
            document.getElementById('loading').classList.add('hidden');
            document.getElementById('results').innerHTML = '<p>There was an error processing your request</p>';
            document.getElementById('results').classList.remove('hidden');
        });
});

function searchGoogleScholar(articleTitle) {
    const searchQuery = encodeURIComponent(articleTitle);
    const url = `https://scholar.google.com/scholar?q=${searchQuery}`;
    window.open(url, '_blank');
}

function saveFavorite(title, author, date) {
    const articleData = {
        title: title,
        author: author,
        date: date
    };

    fetch('http://localhost:8080/api/article/save-article', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(articleData),
    })
        .then(response => {
            if (response.ok) {
                alert('Article saved to favorites');
            } else {
                alert('Failed to save article');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Error saving article');
        });
}

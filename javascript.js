const cards = [{
    'name': 'picture1',
    'img': 'p1.jpg',
},
{
    'name': 'picture2',
    'img': 'p2.jpg',
},
{
    'name': 'picture3',
    'img': 'p3.jpg',
},
{
    'name': 'picture4',
    'img': 'p4.jpg',
},
{
    'name': 'picture5',
    'img': 'p5.jpg',
},
{
    'name': 'picture6',
    'img': 'p6.jpg',
}
];


//random düzen 
const gameGrid = cards
    .concat(cards)
    .sort(() => 0.5 - Math.random()); 

let firstGuess = '';
let secondGuess = '';
let count = 0;  
let previousTarget = null;
let delay = 1200;
let point = 0;
let trueCount = 0;

// oyun elementi getir
const game = document.getElementById('game');
//section oluştur
const grid = document.createElement('section');
//class ata grid
grid.setAttribute('class', 'grid');

game.appendChild(grid);


gameGrid.forEach(item => {
    const { name, img } = item;

    const card = document.createElement('div');
    card.classList.add('card');
    card.dataset.name = name;

    const front = document.createElement('div');
    front.classList.add('front');

    const back = document.createElement('div');
    back.classList.add('back');
    back.style.backgroundImage = `url(${img})`; //fotoğrafları ekrana getirince göstermeyi sağlar

    grid.appendChild(card);
    card.appendChild(front);
    card.appendChild(back);
});

const match = () => {  //eşleşme
    const selected = document.querySelectorAll('.selected');
    selected.forEach(card => {
        card.classList.add('match');
        card.classList.add('hide');
    });
    // tümü dogru ise 
    if (trueCount==cards.length) {
        winGame();
        clearInterval(timerStart); // süreyi durdurur
    } 
    
};

const resetGuesses = () => { //tekrardan seçilen kartların kapanmasını
    firstGuess = '';
    secondGuess = '';
    count = 0;
    previousTarget = null;
    var selected = document.querySelectorAll('.selected');
    selected.forEach(card => {
    card.classList.remove('selected');
    });
       
};


var elementTimer = document.getElementById("timer");
var snTime = 180;
var timedk = 3;
var timesn = 60;
function timerCount() {
    if(snTime %60 == 0 )
    {
        timedk--;
        timesn=59;
    }else
    {
        timesn--;
    }
    snTime--;
    
    if (snTime == 0) { 
        clearInterval(timerStart); //timer durdurma fonksiyonu 
        snTime=0;
        gameOver();
    }
   
   elementTimer.innerHTML = timedk+":"+timesn;
}

function restartGame() {        
    window.location = "./index.html";
}

function gameOver() {
    statebar.style.visibility="hidden";
    newgame.style.visibility = "hidden";
    uyari.style.display="block";
    
}

var uyari=document.getElementById("uyari");
var newgame = document.getElementById("game");

var pointGame = document.getElementById("pointGame");

console.log(newgame);


var statebar = document.getElementById("statebar");


function winGame() {
   
    uyari.style.display="block";
    statebar.style.visibility="hidden";
    
    
    let kazandin = document.getElementById("timewarning");
    console.log(kazandin);
    kazandin.innerHTML = "Oyunu Kazandınız!";

    let zaman = document.getElementById("timewrite");
    zaman.innerHTML = "Kalan Süre: " + elementTimer.innerHTML;
    
    pointGame.innerHTML = "Puanınız: " + elementPuan.innerHTML;
    statebar.style.visibility="hidden";
    
}



var timerStart = setInterval(timerCount, 1000);


// span elementine ulaş ve puanı eklenek için elementi degişkene ata
var elementPuan = document.getElementById("spanPoint");


grid.addEventListener('click', event => {

    const clicked = event.target;

    if (
        clicked.nodeName === 'SECTION' ||
        clicked === previousTarget ||
        clicked.parentNode.classList.contains('selected') ||
        clicked.parentNode.classList.contains('match')
    ) {
        return;
    }

    if (count < 2) {
        count++;
        if (count === 1) {
            firstGuess = clicked.parentNode.dataset.name;
            console.log(firstGuess);
   
            clicked.parentNode.classList.add('selected');
        } else {
            secondGuess = clicked.parentNode.dataset.name;
            console.log(secondGuess);

            clicked.parentNode.classList.add('selected');
        }

        if (firstGuess && secondGuess) {
            if (firstGuess === secondGuess) {
                //dogru ise truecount artır
                trueCount++; 
     
               //dogru ise puana 10 ekle 
                point = point + 10;
                elementPuan.innerText = point;
                setTimeout(match, delay);
                
            }
            else{
                
                console.log(firstGuess.classList);
                console.log(secondGuess.classList);
            }
            console.log()
            
            setTimeout(resetGuesses, delay);
        }
        previousTarget = clicked;
    }

});

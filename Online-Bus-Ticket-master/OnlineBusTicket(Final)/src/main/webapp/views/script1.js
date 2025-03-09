function Driver(){
    var v1=document.getElementById("c1").innerHTML;
    var v2=document.getElementById("c2").innerHTML;
    var v3=document.getElementById("c3").innerHTML;
    var v4=document.getElementById("c4").innerHTML;

    v1=parseInt(v1);
    v2=parseInt(v2);
    v3=parseInt(v3);
    v4=parseInt(v4);
    console.log(v1,v2,v3,v4);


    var N1=document.getElementById("N1").innerHTML; 
    var N2=document.getElementById("N2").innerHTML;
    var N3=document.getElementById("N3").innerHTML;
    var N4=document.getElementById("N4").innerHTML;


    
    var obj = {"Yadhu":v4,"Om preethu":v3,"Farish":v2,"Mahesh":v1};

    var keys = Object.keys(obj);
    var lowest = Math.min.apply(null, keys.map(function(x) { return obj[x]} ));
    var match  = keys.filter(function(y) { return obj[y] === lowest });
    console.log(match, lowest);
    console.log(N1,N2,N3,N4);
    alert( match+ "  has to be assign for bus no 1");
}
function a(){
    document.getElementById("c1").innerHTML =  document.getElementById("a1").value ;
    document.getElementById("c2").innerHTML =  document.getElementById("a2").value ;
    document.getElementById("c3").innerHTML =  document.getElementById("a3").value ;
    document.getElementById("c4").innerHTML =  document.getElementById("a4").value ;
}
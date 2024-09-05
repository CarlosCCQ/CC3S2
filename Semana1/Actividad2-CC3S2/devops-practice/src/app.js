const express = require('express'); //Se guarda en express el modulo importado Express, este es un framework para Node.js
const app = express(); //Se llama a la funcion express, que inicializa una nueva aplicacion Express, esta aplicacion se asigna a la constante 'app'
const client = require('prom-client');

const collectDefaultMetrics = client.collectDefaultMetrics; // Extrae la funcion collectDefaultMetrics del cliente Prometheus
collectDefaultMetrics({timeout:5000}); // Recolecta las metricas cada 5 segundos

//Define un endpoint para exponer las metricas en la ruta '/metrics'
app.get('/metrics', async (req, res) => { // La función que maneja la ruta '/metrics' es una función asincrona 
    res.set('Content-Type', client.register.contentType); // Establece el encabezado Content-Type a 'text/plain' 
    res.end(await client.register.metrics()); // Genera y envia metricas al cliente
});

app.get('/',(req,res) => { //Define la ruta GET en la raiz ('/') que responde con 'Hello, World!'
    res.send('Hello, World!'); //Envia la respuesta 'Hello, World!' al cliente cuando se accede a la ruta raiz ('/') 
});

module.exports = app; //Exporta la aplicacion 'app' para que pueda ser utilizada en otros modulos de Node.js
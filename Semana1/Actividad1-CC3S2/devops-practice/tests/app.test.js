// const request = require('supertest');//Import la libreria 'supertest', que se utiliza para realizar pruebas en aplicaciones web HTTP 
// const app = require('../src/app');//Se importa Express desde app.js de la carpeta src

// let server; //Se utiliza para almacenar la instancia del servidor Express, esta se iniciara al ejecutar las pruebas y finalizara al finalizar las pruebas

// beforeAll((done) => {
//     server = app.listen(done); //Para iniciar el servidor en un puerto dinamico
// });

// afterAll((done) => {
//     server.close(done); //Para cerrar el servidor despues de todas las pruebas
// });

// describe('GET /',() => {//Se esta agrupando un conjunto de pruebas para la ruta 'GET /'
//     it('should return Hello, World!', async () => {//Se define el test, el primer argumento es lo que deberia hacer el codigo, el segundo argumento es la logica de la prueba asincronica
//         const res = await request(app).get('/');//await espera a que se complete la solicitud HTTP
//         expect(res.statusCode).toEqual(200);//Se verifica el estado HTTP en la respuesta
//         expect(res.text).toBe('Hello, World!');//Aqui se verifica que res.text sea exactamente 'Hello, World!'
//     });
// });

const request = require('supertest');
const app = require('../src/app');

describe('GET /', () => {
    let server;

    beforeAll(() => {
        sever = app.listen(0); // Usar 0 permite al sistema asignar un puerto libre automatico
    });

    afterAll(() => {
        server.close(); // Cierra el servidor despues de que las pruebas hayan terminado
    });

    it('should return Hello, World!', async () => {
        const res = await request(app).get('/');
        expect(res.statusCode).toEqual(200);
        expect(res.text).toBe('Hello, World!');
    });
});

describe('GET /delay', () => {
    let server;

    beforeAll(() => {
        server = app.listen(0);
    });

    afterAll(() => {
        server.close();
    });

    it('should return after a delay', async () => {
        const start = Date.now();
        const res = await request(app).get('/delay');
        const end = Date.now();
        const duration = end - start;

        expect(res.statusCode).toEqual(200);
        expect(res.text).toBe('This was delayed by 2 seconds');
        expect(duration).toBeGreaterThanOrEqual(2000);
    });
});

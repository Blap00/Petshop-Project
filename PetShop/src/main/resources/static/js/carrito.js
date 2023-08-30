// carrito.js
const carro = [];

class CarritoDeCompras {
    constructor(producto, cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }
}

function agregarAlCarro(producto, cantidad) {
    const codigoDescuento = prompt('Ingresa el código de descuento:');
    if (codigoDescuento) {
        const carritoItem = new CarritoDeCompras(producto, cantidad);
        
        // Agrega el código de descuento al objeto carritoItem
        carritoItem.codigoDescuento = codigoDescuento;

        fetch('/add-to-cart', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(carritoItem)
        }).then(response => {
            if (response.ok) {
                // Producto agregado al carrito
                actualizarCarritoEnInterfaz();
            } else {
                console.error('Error al agregar el producto al carrito');
            }
        });
    }
}


function eliminarDelCarro(index) {
    fetch(`/remove-from-cart/${index}`, {
        method: 'DELETE'
    }).then(response => {
        if (response.ok) {
            // Producto eliminado del carrito
            actualizarCarritoEnInterfaz();
        } else {
            console.error('Error al eliminar el producto del carrito');
        }
    });
}

function actualizarCarritoEnInterfaz() {
    const carritoItemsDiv = document.getElementById('carroItems');
    carritoItemsDiv.innerHTML = ''; // Limpiar contenido anterior

    carro.forEach((item, index) => {
        const itemDiv = document.createElement('div');
        itemDiv.innerHTML = `${item.cantidad} x ${item.producto}`;
        const eliminarBtn = document.createElement('button');
        eliminarBtn.textContent = 'Eliminar';
        eliminarBtn.addEventListener('click', () => {
            eliminarDelCarro(index);
        });
        itemDiv.appendChild(eliminarBtn);
        carritoItemsDiv.appendChild(itemDiv);
    });
}


// Escuchar el clic en el botón de aplicar descuento
document.getElementById('btnAplicarDescuento').addEventListener('click', async () => {
    const codigoDescuento = prompt('Ingresa el código de descuento:');
    if (codigoDescuento) {
        const descuento = await validarCodigoDescuento(codigoDescuento);
        if (descuento) {
            calcularDescuentoYActualizarInterfaz(descuento.porcentaje);
        } else {
            console.error('Código de descuento inválido');
        }
    }
});


async function calcularDescuentoYActualizarInterfaz(codigoDescuento) {
    const response = await fetch('/calculate-discount', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ discountCode: codigoDescuento }) // Cambia el nombre del campo si es diferente en tu modelo
    });

    if (response.ok) {
        const totalConDescuento = await response.json();
        actualizarTotalEnInterfaz(totalConDescuento);
    } else {
        console.error('Error al calcular el descuento');
    }
}

function actualizarTotalEnInterfaz(total) {
    const totalDiv = document.getElementById('total');
    totalDiv.textContent = `Total con descuento: $${total.toFixed(2)}`; // Actualiza el elemento en tu HTML que muestra el total
}

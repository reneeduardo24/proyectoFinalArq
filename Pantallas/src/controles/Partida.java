/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controles;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import mensaje.MensajeAbandonarPartida;
import mensaje.MensajeMovimiento;
import mensaje.MensajePartida;
import mensaje.PartidaVisitador;
import timbiriche.Cliente;
import timbiriche.Cuadro;
import timbiriche.IObservador;
import timbiriche.Jugador;
import timbiriche.Linea;
import timbiriche.Nodo;

/**
 *
 * @author rene_
 */
public class Partida extends PartidaVisitador implements Runnable {

    private ServerSocket server;
    private List<Linea> listaLineas;
    private List<IObservador> listaObservadores;
    private Cliente jugador;
    private List<Cliente> listaDeJugadores;
    private List<Nodo> listaDeNodos;
    private List<Cuadro> listaDeCuadros;
    private JTable tablaPuntuacion;
    private int turno;
    private final int NODOS_DOS_JUGADORES = 10;
    private final int NODOS_TRES_JUGADORES = 20;
    private final int NODOS_CUATRO_JUGADORES = 40;
    private final int DOS_JUGADORES = 2;
    private final int TRES_JUGADORES = 3;
    private final int CUATRO_JUGADORES = 4;
    private int anchoTablero;

    public Partida(Cliente jugador) {
        try {
            this.server = new ServerSocket(jugador.getPuertoCliente());
        } catch (IOException ex) {
            Logger.getLogger(Partida.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.listaObservadores = new ArrayList<>();
    }
    
    public void notificarObservadores() {
        for (IObservador iteradorObservador : listaObservadores) {
            iteradorObservador.actualizar();
        }
    }
    
    public void agregarObservador(IObservador observador) {
        this.listaObservadores.add(observador);
    }

    private ServerSocket getServer() {
        return server;
    }

    private void setServer(ServerSocket server) {
        this.server = server;
    }

    public List<Linea> getListaLineas() {
        return listaLineas;
    }

    public void setListaLineas(List<Linea> listaLineas) {
        this.listaLineas = listaLineas;
    }

    public List<IObservador> getListaObservadores() {
        return listaObservadores;
    }

    public void setListaObservadores(List<IObservador> listaObservadores) {
        this.listaObservadores = listaObservadores;
    }

    public Cliente getJugador() {
        return jugador;
    }

    public void setJugador(Cliente jugador) {
        this.jugador = jugador;
    }

    public List<Cliente> getListaDeJugadores() {
        return listaDeJugadores;
    }

    public void setListaDeJugadores(List<Cliente> listaDeJugadores) {
        this.listaDeJugadores = listaDeJugadores;
    }

    public List<Nodo> getListaDeNodos() {
        return listaDeNodos;
    }

    public void setListaDeNodos(List<Nodo> listaDeNodos) {
        this.listaDeNodos = listaDeNodos;
    }

    public List<Cuadro> getListaDeCuadros() {
        return listaDeCuadros;
    }

    public void setListaDeCuadros(List<Cuadro> listaDeCuadros) {
        this.listaDeCuadros = listaDeCuadros;
    }

    public JTable getTablaPuntuacion() {
        return tablaPuntuacion;
    }

    public void setTablaPuntuacion(JTable tablaPuntuacion) {
        this.tablaPuntuacion = tablaPuntuacion;
    }

    public int getAnchoTablero() {
        return anchoTablero;
    }

    public void setAnchoTablero(int anchoTablero) {
        this.anchoTablero = anchoTablero;
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }
    
    //**********************************************************************************
    public int obtenerNumeroDeNodosPorLinea() {
        int numeroDeJugadores = this.listaDeJugadores.size();
        int numeroDeNodosPorLinea = 0;
        if(numeroDeJugadores == DOS_JUGADORES) {
            numeroDeNodosPorLinea = NODOS_DOS_JUGADORES;
        }
        
        if(numeroDeJugadores == TRES_JUGADORES) {
            numeroDeNodosPorLinea = NODOS_TRES_JUGADORES;
        }
        if(numeroDeJugadores == CUATRO_JUGADORES) {
            numeroDeNodosPorLinea = NODOS_CUATRO_JUGADORES;
        }
        return numeroDeNodosPorLinea;
    }
    
    /**
     * 
     */
    public void crearNodosDeTablero() {
        int numeroDeNodosPorLinea = this.obtenerNumeroDeNodosPorLinea();        
        int espacioEntreNodos = anchoTablero/numeroDeNodosPorLinea;
        int ejeX;
        int ejeY=20;
        
        Nodo nuevoNodo;
        for (int i = 0; i < numeroDeNodosPorLinea; i++) {
            ejeX= 20;
            for (int j = 0; j < numeroDeNodosPorLinea; j++) {
                nuevoNodo = new Nodo(ejeX, ejeY);
                listaDeNodos.add(nuevoNodo);
                ejeX += espacioEntreNodos;
            }
            ejeY += espacioEntreNodos;
        }        
    }
    
    /**
     * 
     * @param lineaNueva
     */
    public void actualizarPuntaje(Linea lineaNueva) {
        int numeroDeCuadros = this.validarCuadro(lineaNueva);
        int puntos;
        if (numeroDeCuadros > 0) {
            puntos = (Integer) this.tablaPuntuacion.getValueAt(0, this.listaDeJugadores.indexOf(this.listaDeJugadores.get(turno)));
            puntos = puntos + numeroDeCuadros;
            this.tablaPuntuacion.setValueAt(puntos, 0, this.listaDeJugadores.indexOf(this.listaDeJugadores.get(turno)));
        } else {
            this.actualizarTurnoDeJugador();
        }
    }
    
    public boolean isTableroLleno() {
        int tableroLleno = (this.obtenerNumeroDeNodosPorLinea()*(this.obtenerNumeroDeNodosPorLinea()-1))*2;
        return tableroLleno == this.listaLineas.size();
    }
    
    /**
     * 
     * @return 
     */
    public int obtenerPuntajeMasAlto() {
        int puntosMayor = (Integer) this.tablaPuntuacion.getValueAt(0, this.listaDeJugadores.indexOf(this.listaDeJugadores.get(0)));
        int puntosJugador;
        for (int i = 1; i < this.listaDeJugadores.size(); i++) {
            puntosJugador = (Integer) this.tablaPuntuacion.getValueAt(0, this.listaDeJugadores.indexOf(this.listaDeJugadores.get(i)));
            if(puntosJugador > puntosMayor) {
                puntosMayor = puntosJugador;
            }
        }
        return puntosMayor;
    }
    
    /**
     * 
     */
    public void actualizarTurnoDeJugador() {
        if (this.turno == this.listaDeJugadores.size()-1) {
            this.turno = -1;
        } 
        this.turno++;
    }
    
    public boolean isTurnoJugador() {
        Jugador jugadorNuevo = (Jugador) this.listaDeJugadores.get(this.turno);
        return jugadorNuevo.equals(this.jugador);
    }
    
    public double obtenerDistanciaEntreNodos() {
        return this.listaDeNodos.get(1).getEjeX() - this.listaDeNodos.get(0).getEjeX();
    }
    
    /**
     * 
     * @param lineaNueva
     * @return 
     */
    public int validarCuadro(Linea lineaNueva) {
        Nodo nodoInicial = this.listaDeNodos.get(this.listaDeNodos.indexOf(new Nodo(lineaNueva.getEjeXPuntoInicial(), lineaNueva.getEjeYPuntoInicial())));
        Nodo nodoFin = this.listaDeNodos.get(this.listaDeNodos.indexOf(new Nodo(lineaNueva.getEjeXPuntoFinal(), lineaNueva.getEjeYPuntoFinal())));
        Nodo nodoAuxiliar;
        Cuadro cuadro;
        int numeroDeCuadros=0;
        double distanciaEntreNodos = obtenerDistanciaEntreNodos();
        // Valida linea de izquierda a derecha
        if (nodoInicial.getEjeX() < nodoFin.getEjeX()) {
            //Valida si existe un cuadro arriba de la linea
            nodoAuxiliar = nodoFin.getNodoArriba();
            if (nodoAuxiliar != null) {
                nodoAuxiliar = nodoAuxiliar.getNodoIzquierda();
                if (nodoAuxiliar != null) {
                    cuadro = new Cuadro(((Jugador)this.listaDeJugadores.get(this.turno)).getColor(),nodoAuxiliar.getEjeX(), nodoAuxiliar.getEjeY(),distanciaEntreNodos);
                    nodoAuxiliar = nodoAuxiliar.getNodoAbajo();
                    if (nodoAuxiliar != null) {
                        this.listaDeCuadros.add(cuadro);
                        numeroDeCuadros++;
                    }
                }
            }
            //Valida si existe un cuadro debajo de la linea
            nodoAuxiliar = nodoFin.getNodoAbajo();
            if (nodoAuxiliar != null) {
                nodoAuxiliar = nodoAuxiliar.getNodoIzquierda();
                if (nodoAuxiliar != null) {
                    nodoAuxiliar = nodoAuxiliar.getNodoArriba();
                    if (nodoAuxiliar != null) {
                        cuadro = new Cuadro(((Jugador)this.listaDeJugadores.get(this.turno)).getColor(),nodoAuxiliar.getEjeX(), nodoAuxiliar.getEjeY(),distanciaEntreNodos);
                        this.listaDeCuadros.add(cuadro);
                        numeroDeCuadros++;
                    }
                }
            }
        //Valida la linea de derecha a izquierda
        } else if (nodoInicial.getEjeX() > nodoFin.getEjeX()){
            //Valida si existe un cuadro arriba de la linea
            nodoAuxiliar = nodoFin.getNodoArriba();
            if(nodoAuxiliar != null) {
                cuadro = new Cuadro(((Jugador)this.listaDeJugadores.get(this.turno)).getColor(),nodoAuxiliar.getEjeX(), nodoAuxiliar.getEjeY(),distanciaEntreNodos);
                nodoAuxiliar = nodoAuxiliar.getNodoDerecha();
                if(nodoAuxiliar != null) {
                    nodoAuxiliar = nodoAuxiliar.getNodoAbajo();
                    if(nodoAuxiliar != null) {
                        this.listaDeCuadros.add(cuadro);
                        numeroDeCuadros++;
                    }
                }
            }
            //Valida si existe un cuadro debajo de la linea
            nodoAuxiliar = nodoFin.getNodoAbajo();
            if (nodoAuxiliar != null) {
                cuadro = new Cuadro(((Jugador)this.listaDeJugadores.get(this.turno)).getColor(),nodoFin.getEjeX(), nodoFin.getEjeY(),distanciaEntreNodos);
                nodoAuxiliar = nodoAuxiliar.getNodoDerecha();
                if (nodoAuxiliar != null) {
                    nodoAuxiliar = nodoAuxiliar.getNodoArriba();
                    if (nodoAuxiliar != null) {
                        this.listaDeCuadros.add(cuadro);
                        numeroDeCuadros++;
                    }
                }
            }
        }
        
        // Valida linea de arriba a abajo
        
        //Valida si existe un cuadro a la derecha
        if(nodoInicial.getEjeY() < nodoFin.getEjeY()) {
            nodoAuxiliar = nodoFin.getNodoDerecha();
            if (nodoAuxiliar != null) {
                nodoAuxiliar = nodoAuxiliar.getNodoArriba();
                if (nodoAuxiliar != null) {
                    nodoAuxiliar = nodoAuxiliar.getNodoIzquierda();
                    if (nodoAuxiliar != null) {
                        cuadro = new Cuadro(((Jugador)this.listaDeJugadores.get(this.turno)).getColor(),nodoAuxiliar.getEjeX(), nodoAuxiliar.getEjeY(),distanciaEntreNodos);
                        this.listaDeCuadros.add(cuadro);
                        numeroDeCuadros++;
                    }
                }
            }
            //Valida si existe un cuadro a la izquierda
            nodoAuxiliar = nodoFin.getNodoIzquierda();
            if (nodoAuxiliar != null) {
                nodoAuxiliar = nodoAuxiliar.getNodoArriba();
                if (nodoAuxiliar != null) {
                    cuadro = new Cuadro(((Jugador)this.listaDeJugadores.get(this.turno)).getColor(),nodoAuxiliar.getEjeX(), nodoAuxiliar.getEjeY(),distanciaEntreNodos);
                    nodoAuxiliar = nodoAuxiliar.getNodoDerecha();
                    if (nodoAuxiliar != null) {
                        this.listaDeCuadros.add(cuadro);
                        numeroDeCuadros++;
                    }
                }
            }
        // Valida linea de abajo a arriba
        } else if(nodoInicial.getEjeY() > nodoFin.getEjeY()) {
            //Valida si existe linea a la derecha
            nodoAuxiliar = nodoFin.getNodoDerecha();
            if (nodoAuxiliar != null) {
                cuadro = new Cuadro(((Jugador)this.listaDeJugadores.get(this.turno)).getColor(),nodoFin.getEjeX(), nodoFin.getEjeY(),distanciaEntreNodos);
                nodoAuxiliar = nodoAuxiliar.getNodoAbajo();
                if (nodoAuxiliar != null) {
                    nodoAuxiliar = nodoAuxiliar.getNodoIzquierda();
                    if (nodoAuxiliar != null) {
                        this.listaDeCuadros.add(cuadro);
                        numeroDeCuadros++;
                    }
                }
            }
            
            //Valida si existe un cuadro a la izquierda
            nodoAuxiliar = nodoFin.getNodoIzquierda();
            if (nodoAuxiliar != null) {
                cuadro = new Cuadro(((Jugador)this.listaDeJugadores.get(this.turno)).getColor(),nodoAuxiliar.getEjeX(), nodoAuxiliar.getEjeY(),distanciaEntreNodos);
                nodoAuxiliar = nodoAuxiliar.getNodoAbajo();
                if (nodoAuxiliar != null) {
                    nodoAuxiliar = nodoAuxiliar.getNodoDerecha();
                    if (nodoAuxiliar != null) {
                        this.listaDeCuadros.add(cuadro);
                        numeroDeCuadros++;
                    }
                }
            }
        }
        return numeroDeCuadros;
    }
    //**********************************************************************************
    @Override
    public void run() {
        try {
            Socket socketCliente;
            while (true) {
                socketCliente = server.accept();
                ObjectInputStream ois = new ObjectInputStream(socketCliente.getInputStream());
                MensajePartida mensajeRecibido = (MensajePartida)ois.readObject();
                mensajeRecibido.accept(this);
                ois.close();
                socketCliente.close();
            }
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex.getStackTrace());
        }
    }
    
    /**
     * 
     * @param lineaNueva 
     */
    public void asignarNodos(Linea lineaNueva) {
        Nodo nodoInicial = this.listaDeNodos.get(this.listaDeNodos.indexOf(new Nodo(lineaNueva.getEjeXPuntoInicial(), lineaNueva.getEjeYPuntoInicial())));
        Nodo nodoFin = this.listaDeNodos.get(this.listaDeNodos.indexOf(new Nodo(lineaNueva.getEjeXPuntoFinal(), lineaNueva.getEjeYPuntoFinal())));
        if(nodoInicial.getEjeX() < nodoFin.getEjeX()) {
            nodoInicial.setNodoDerecha(nodoFin);
            nodoFin.setNodoIzquierda(nodoInicial);
            return;
        } else if (nodoInicial.getEjeX() > nodoFin.getEjeX()){
            nodoInicial.setNodoIzquierda(nodoFin);
            nodoFin.setNodoDerecha(nodoInicial);
            return;
        }
        
        if(nodoInicial.getEjeY() < nodoFin.getEjeY()) {
            nodoInicial.setNodoAbajo(nodoFin);
            nodoFin.setNodoArriba(nodoInicial);
        } else if(nodoInicial.getEjeY() > nodoFin.getEjeY()) {
            nodoInicial.setNodoArriba(nodoFin);
            nodoFin.setNodoAbajo(nodoInicial);
        }        
    }
    
    public void eliminarAsignacionDeNodos(Linea linea) {
        Nodo nodoInicio = this.listaDeNodos.get(this.listaDeNodos.indexOf(new Nodo(linea.getEjeXPuntoInicial(), linea.getEjeYPuntoInicial())));
        Nodo nodoFinal = this.listaDeNodos.get(this.listaDeNodos.indexOf(new Nodo(linea.getEjeXPuntoFinal(), linea.getEjeYPuntoFinal())));
        
        if(isIzquierdaADerecha(nodoInicio, nodoFinal)) {
            nodoInicio.setNodoDerecha(null);
            nodoFinal.setNodoIzquierda(null);
        } else if(isDerechaAIzquierda(nodoInicio, nodoFinal)) {
            nodoInicio.setNodoIzquierda(null);
            nodoFinal.setNodoDerecha(null);
        } else if(isArribaAAbajo(nodoInicio, nodoFinal)) {
            nodoInicio.setNodoAbajo(null);
            nodoFinal.setNodoArriba(null);
        } else if (isAbajoAArriba(nodoInicio, nodoFinal)) {
            nodoInicio.setNodoArriba(null);
            nodoFinal.setNodoAbajo(null);
        }
    }
    
    public boolean isIzquierdaADerecha(Nodo nodoInicio, Nodo nodoFinal) {
        return (nodoInicio.getEjeX() < nodoFinal.getEjeX());
    }
    
    public boolean isDerechaAIzquierda(Nodo nodoInicio, Nodo nodoFinal) {
        return (nodoInicio.getEjeX() > nodoFinal.getEjeX());
    }
    
    public boolean isArribaAAbajo(Nodo nodoInicio, Nodo nodoFinal) {
        return (nodoInicio.getEjeY() > nodoFinal.getEjeY());
    }
    
    public boolean isAbajoAArriba(Nodo nodoInicio, Nodo nodoFinal) {
        return (nodoInicio.getEjeY() < nodoFinal.getEjeY());
    }
    
    
    public void eliminarJugador(Jugador jugadorAbandonar) {
        List<Linea> lineaLista = new ArrayList<>(listaLineas);
        List<Cuadro> cuadrosLista = new ArrayList<>(listaDeCuadros);
        for (Linea lineaIterador : lineaLista) {
            if(lineaIterador.getColor().equals(jugadorAbandonar.getColor())) {
                this.eliminarAsignacionDeNodos(this.listaLineas.get(this.listaLineas.indexOf(lineaIterador)));
                this.listaLineas.remove(lineaIterador);
            }
        }
        for (Cuadro cuadroIterador : cuadrosLista) {
            if(cuadroIterador.getColor().equals(jugadorAbandonar.getColor())) {
                this.listaDeCuadros.remove(cuadroIterador);
            }
        }
    }

    @Override
    public void visitarMensajeAbandonarPartida(MensajeAbandonarPartida mensajeAbandonarPartida) {
        Cliente jugadorAbandonar = mensajeAbandonarPartida.getJugador();
        int indexJugador = this.listaDeJugadores.indexOf((Jugador)mensajeAbandonarPartida.getJugador());
        TableColumn columnaABorrar = tablaPuntuacion.getColumnModel().getColumn(indexJugador);
        this.tablaPuntuacion.removeColumn(columnaABorrar);
        if(this.listaDeJugadores.get(this.turno).equals(jugadorAbandonar)) {
            this.actualizarTurnoDeJugador();
        }
        this.listaDeJugadores.remove((Jugador)jugadorAbandonar);
        this.eliminarJugador((Jugador) jugadorAbandonar);
        notificarObservadores();
    }

    @Override
    public void visitarMensajeMovimiento(MensajeMovimiento mensajeMovimeinto) {
        this.listaLineas.add(mensajeMovimeinto.getLinea());
        notificarObservadores();
    }

}

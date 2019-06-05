import axios from 'axios';

export const FETCH_ROOM_GRID_INFO_STARTED = 'FETCH_ROOM_GRID_INFO_STARTED';
export const FETCH_ROOM_GRID_INFO_SUCCESS = 'FETCH_ROOM_GRID_INFO_SUCCESS';
export const FETCH_ROOM_GRID_INFO_FAILURE = 'FETCH_ROOM_GRID_INFO_FAILURE';


export const fetchRoomGrid = ({name, grid}) => {
  return dispatch => {
    dispatch(fetchRoomGridInfo(name, grid)); // antes de fazer o get, coloca o loading a true
    const data = {name, grid};
    axios
      .post('http://localhost:9898/gridSettings/grids/'+grid, data, //falta autorização
        {
          headers: {'Content-Type': 'application/json'},
          body: {name}
        })
      .then(res => {
        dispatch(fetchRoomGridInfoSuccess(res.data)); // chegaram os resultados (dados) , loading fica a falso
      })
      .catch(err => {
        dispatch(fetchRoomGridInfoFailure(err.message));
      });
  };
};


export function fetchRoomGridInfo(name, grid) {
  return {
    type: FETCH_ROOM_GRID_INFO_STARTED,
    payload: {
      name: name,
      grid: grid
    }
  }
}

export function fetchRoomGridInfoSuccess(data) { // cria uma açao
  return {
    type: FETCH_ROOM_GRID_INFO_SUCCESS,
    payload: {
      room: data //passa o array com os dados
    }
  }
}

export function fetchRoomGridInfoFailure(message) {
  return {
    type: FETCH_ROOM_GRID_INFO_FAILURE,
    payload: {
      error: message
    }
  }
}




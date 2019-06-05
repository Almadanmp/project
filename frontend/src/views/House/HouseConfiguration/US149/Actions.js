import axios from 'axios';

export const REMOVE_GRID_ROOM_INFO_STARTED = 'REMOVE_GRID_ROOM_INFO_STARTED';
export const REMOVE_GRID_ROOM_INFO_SUCCESS = 'REMOVE_GRID_ROOM_INFO_SUCCESS';
export const REMOVE_GRID_ROOM_INFO_FAILURE = 'REMOVE_GRID_ROOM_INFO_FAILURE';



export const fetchRoomFromGrid = ({roomId, gridId}) => {
  return dispatch => {
    dispatch(fetchRoomInfo(roomId, gridId)); // antes de fazer o get, coloca o loading a true
    axios
      .delete('http://localhost:9898/gridSettings/grids'+gridId, //falta autorização
        {
          headers: {'Content-Type': 'application/json'},
          body: {roomId}
        })
      .then(res => {
        dispatch(fetchRoomInfoSuccess(res.data)); // chegaram os resultados (dados) , loading fica a falso
      })
      .catch(err => {
        dispatch(fetchRoomInfoFailure(err.message));
      });
  };
};


export function fetchRoomInfo(roomId, gridId) {
  return {
    type: REMOVE_GRID_ROOM_INFO_STARTED,
    payload: {
      roomId: roomId,
      gridId: gridId,
    }
  }
}

export function fetchRoomInfoSuccess(data) { // cria uma açao
  return {
    type: REMOVE_GRID_ROOM_INFO_SUCCESS,
    payload: {
      message: data,
    }
  }
}

export function fetchRoomInfoFailure(message) {
  return {
    type: REMOVE_GRID_ROOM_INFO_FAILURE,
    payload: {
      error: message
    }
  }
}

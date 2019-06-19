import {
  REMOVE_GRID_ROOM_INFO_FAILURE,
  REMOVE_GRID_ROOM_INFO_SUCCESS,
  REMOVE_GRID_ROOM_INFO_STARTED,
  FETCH_NO_ROOM_GRID_DATA,

} from './Actions'

const initialstate = {
  loading: false,
  error: null,
  room: []
};


export default function Reducers105 (state = initialstate, action) {
  switch (action.type) {
    case REMOVE_GRID_ROOM_INFO_STARTED:
      return {
        ...state,
        loading: true,
        error: null,
        room: []
      };
    case REMOVE_GRID_ROOM_INFO_SUCCESS:
      return {
        ...state,
        loading: false,
        error: null,
        room: [...action.payload.room]
      };
    case REMOVE_GRID_ROOM_INFO_FAILURE:
      return {
        ...state,
        loading: false,
        error: action.payload.error,
        room: "ERROR: " + action.payload.error,
      };
    case FETCH_NO_ROOM_GRID_DATA:
      return {
        ...state,
        loading: false,
        errorData: action.payload.error,
        room: "ERROR: " + action.payload.error
      };
    default:
      return state;
  }
}

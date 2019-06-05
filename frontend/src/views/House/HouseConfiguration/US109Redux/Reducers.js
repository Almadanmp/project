import {
  FETCH_EDIT_ROOM_STARTED,
  FETCH_EDIT_ROOM_SUCCESS,
  FETCH_EDIT_ROOM_FAILURE,

} from './Actions'


const initialstate = {
  loading: false,
  error: null,
  editRoom: {},
};


export default function Reducer109(state = initialstate, action) {
  switch (action.type) {
    case FETCH_EDIT_ROOM_STARTED:
      return {
        ...state,
        loading: true,
        error: null,
        editRoom: {},
        };
    case FETCH_EDIT_ROOM_SUCCESS:
      return {
        ...state,
        loading: false,
        error: null,
        editRoom: [...action.payload.editRoom]
      };
    case FETCH_EDIT_ROOM_FAILURE:
      return {
        ...state,
        loading: false,
        error: action.payload.error,
        editRoom: {}
      };

    default:
      return state;
  }
}



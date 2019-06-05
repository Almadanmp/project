import {
  FETCH_COLD_STARTED,
  FETCH_COLD_SUCCESS,
  FETCH_COLD_FAILURE,

} from './Actions'


const initialstate = {
  loading: false,
  error: null,
  cold: {}
};


export default function Reducers630(state = initialstate, action) {
  switch (action.type) {
    case FETCH_COLD_STARTED:
      return {
        ...state,
        loading: true,
        error: null,
        cold: {}
      };
    case FETCH_COLD_SUCCESS:
      return {
        ...state,
        loading: false,
        error: null,
        cold: {...action.payload.cold}
      };
    case FETCH_COLD_FAILURE:
      return {
        ...state,
        loading: false,
        error: action.payload.error,
        cold: {}
      };

    default:
      return state;
  }
}



import {
  FETCH_T_STARTED,
  FETCH_T_SUCCESS,
  FETCH_T_FAILURE,

} from './Actions'


const initialstate = {
    loading: false,
    error: null,
    temp: 0
};


export default function Reducers600 (state = initialstate, action) {
  switch (action.type) {
    case FETCH_T_STARTED:
      return {
        ...state,
          loading: true,
          error: null,
          temp: 0
      };
    case FETCH_T_SUCCESS:
      return {
        ...state,
          loading: false,
          error: null,
          temp: action.payload.temp
      };
    case FETCH_T_FAILURE:
      return {
        ...state,
          loading: false,
          error: action.payload.error,
          temp: 0
      };

    default:
      return state;
  }
}



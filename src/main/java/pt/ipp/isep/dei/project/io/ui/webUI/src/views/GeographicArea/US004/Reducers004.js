import {
  FETCH_GABTS_STARTED,
  FETCH_GABTS_SUCCESS,
  FETCH_GABTS_FAILURE,

} from './Actions004'

const initialstate = {
  loading: false,
  error: null,
  listGABTypes: []
};

export default function Reducer002(state = initialstate, action) {
  switch (action.type) {
    case FETCH_GABTS_STARTED:
      return {
        ...state,
        loading: true,
        error: null,
        listGABTypes: []
      };
    case FETCH_GABTS_SUCCESS:
      return {
        ...state,
        loading: false,
        error: null,
        listGABTypes: [...action.payload.listGABTypes]
      };
    case FETCH_GABTS_FAILURE:
      return {
        ...state,
        loading: false,
        error: action.payload.error,
        listGABTypes: " ERROR: NO DATA Available"
      };

    default:
      return state;
  }
}



import {
  FETCH_GAs_BY_TYPE_STARTED,
  FETCH_GAs_BY_TYPE_SUCCESS,
  FETCH_GAs_BY_TYPE_FAILURE,
} from './ActionsUS004'

const initialState = {
  loading: false,
  error: null,
  listGAByType: []
};

export default function ReducerUS004(state = initialState, action) {
  switch (action.type) {
    case FETCH_GAs_BY_TYPE_STARTED:
      return {
        ...state,
        loading: true,
        error: null,
        listGAByType: []
      };
    case FETCH_GAs_BY_TYPE_SUCCESS:
      return {
        ...state,
        loading: false,
        error: null,
        listGAByType: [...action.payload.listGAByType]
      };
    case FETCH_GAs_BY_TYPE_FAILURE:
      return {
        ...state,
        loading: false,
        error: action.payload.error,
        listGATypes: " ERROR: NO DATA Available"
      };

    default:
      return state;
  }
}

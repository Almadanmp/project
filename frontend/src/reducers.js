import {combineReducers} from 'redux';
import Reducers600 from './views/House/HouseMonitoring/USRedux/US600Redux/Reducers600.js';
import Reducers620 from './views/House/HouseMonitoring/USRedux/US620Redux/Reducers620.js';
import Reducer108 from './views/House/HouseConfiguration/US108Redux/Reducers.js';
import Reducers633 from './views/House/HouseMonitoring/USRedux/US633Redux/Reducers633.js';
import Reducers630 from './views/House/HouseMonitoring/USRedux/US630Redux/Reducers630.js';
import Reducers631 from './views/House/HouseMonitoring/USRedux/US631Redux/Reducers631.js';
import Reducers109 from './views/House/HouseConfiguration/US109Redux/Reducers.js';

export default combineReducers({
  Reducers109,
  Reducers630,
  Reducers631,
  Reducers633,
  Reducers600,
  Reducers620,
  Reducer108,
})

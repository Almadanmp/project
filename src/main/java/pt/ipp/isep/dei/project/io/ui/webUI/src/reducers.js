import {combineReducers} from 'redux';
import ReducerUS004 from "./views/GeographicArea/US004/ReducersUS004";
import Reducer108 from './views/House/HouseConfiguration/US108/Reducers.js';
import Reducers600 from './views/House/HouseMonitoring/USRedux/US600Redux/Reducers600.js';
import Reducers620 from './views/House/HouseMonitoring/USRedux/US620Redux/Reducers620.js';
import Reducers630 from './views/House/HouseMonitoring/USRedux/US630Redux/Reducers630.js';
import Reducers631 from './views/House/HouseMonitoring/USRedux/US631Redux/Reducers631.js';
import Reducer002 from "./views/GeographicArea/US002/Reducers002";
import Reducers623 from './views/House/HouseMonitoring/USRedux/US623Redux/Reducers623.js';
import Reducer003 from "./views/GeographicArea/US003/Reducers003";
import ReducersGetRoomsNotInGrid from './views/EnergyGrid/US147/ReducersGetRoomsNotInGrid'

import Reducers633 from './views/House/HouseMonitoring/USRedux/US633Redux/Reducers633.js';

export default combineReducers({
  Reducer002,
  ReducerUS004,
  Reducer108,
  Reducers600,
  Reducers620,
  Reducers623,
  ReducersGetRoomsNotInGrid,
  Reducer003,
  Reducers630,
  Reducers631,
  Reducers633
})

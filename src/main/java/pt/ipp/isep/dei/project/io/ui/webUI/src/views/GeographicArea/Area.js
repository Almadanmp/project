import React, {Component} from 'react';
import US001 from "./US001";
import US002 from './US002';
import US003 from './US003';
import US004 from './US004';
import US006 from './US006';
import US007 from './US007';
import US007Redux from './US007Redux/US007Redux';
import US010 from './US010';
import US011 from './US011';
import AreasList from './TableAreas/AreasList';

class Area extends Component {
  constructor(props) {
    super(props);
    this.toggle = this.toggle.bind(this);
    this.state = {collapse: false};
  }

  toggle() {
    this.setState(state => ({collapse: !state.collapse}));
  }

  render() {
    return (
      <div>
        <h2>Welcome to the Geographic Area Menu</h2>
        <p></p>
        <AreasList/>
        <US001/>
        <US002/>
        <US004/>
        <US006/>
        <US007Redux/>
        <US010/>
        <US011/>
      </div>
    );
  }
}

export default Area;

import React, {Component} from 'react';
import US002 from './US002';
import US003 from './US003';
import US011 from './US011';
import US006 from './US006';
import US004 from './US004';




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
        <h4>Please select the option you want to run</h4>
        <US002/>
        <US003/>
        <US004/>
        <US011/>
        <US006/>
      </div>
    );
  }
}

export default Area;

import React, {Component} from 'react';
import US108 from './US108'
import US109 from './US109'
import US130 from './US130'
import US145 from './US145/US145'
import US147 from './US147'
import US149 from './US149'
import US105 from "./US105";



class Example extends Component {
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
        <h2>Welcome to the House Configuration Menu.</h2>
        <h4>Please select the dick you want to run.</h4>
        <US105/>
        <US108/>
        <US109/>
        <US130/>
        <US145/>
        <US147/>
        <US149/>
      </div>
    );
  }
}

export default Example;

import React, {Component} from 'react';

class UnderMaintenance extends Component {
  constructor(props) {
    super(props);
    this.toggle = this.toggle.bind(this);
    this.state = { collapse: false };
  }

  toggle() {
    this.setState(state => ({ collapse: !state.collapse }));
  }

  render() {
    return (
      <div>
        Under Maintenance!!!
      </div>
    );
  }
}

export default UnderMaintenance;


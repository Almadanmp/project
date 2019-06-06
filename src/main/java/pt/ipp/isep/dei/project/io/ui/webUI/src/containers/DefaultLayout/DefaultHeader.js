import React, { Component } from 'react';
import { Link, NavLink } from 'react-router-dom';
import { Badge, Nav, NavItem } from 'reactstrap';
import PropTypes from 'prop-types';

import { AppAsideToggler, AppNavbarBrand, AppSidebarToggler } from '@coreui/react';
import logo from '../../assets/img/brand/logo.svg'
import sygnet from '../../assets/img/brand/sygnet.svg'

const propTypes = {
  children: PropTypes.node,
};

const defaultProps = {};

class DefaultHeader extends Component {
  render() {

    // eslint-disable-next-line
    const { children, ...attributes } = this.props;

    return (
      <React.Fragment>
        <AppSidebarToggler className="d-lg-none" display="inline-block" mobile />
        <AppSidebarToggler className="d-md-down-none"  />
        <AppNavbarBrand
          full={{ src:'https://imgur.com/rf2xy6y.png', width: 130, height: 50, alt: 'SmartHome Logo' }}
          minimized={{ src: 'https://imgur.com/rf2xy6y.png', width: 60, height: 60, alt: 'SmartHome Logo' }}
        />

        <Nav className="d-md-down-none" >
        </Nav>


      </React.Fragment>

    );
  }
}

DefaultHeader.propTypes = propTypes;
DefaultHeader.defaultProps = defaultProps;

export default DefaultHeader;
